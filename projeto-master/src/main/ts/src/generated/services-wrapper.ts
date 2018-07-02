import { InjectionToken } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Observer } from 'rxjs/Observer';

/**
 * path é o caminho SEM BARRA AO FINAL para o broker. Por padrão é simplesmente 'broker'
 */
export interface BrokerConfiguration {
    path: string,
    realTime: boolean,
    stompPath?: string
}

export let BROKER_CONFIGURATION = new InjectionToken<BrokerConfiguration>('broker.configuration');


/////////////////
/////////////////
/////////////////
/////////////////

export function dwrWrapper(configuration: BrokerConfiguration, serviceName: string, methodName: string, ...args: any[]): Observable<any> {
        return Observable.create((observer: Observer<any>) => {
            loadDwrIfNeeded(configuration).then(() => {
                loadServiceIfNeeded(configuration, serviceName).then(service => {
                    args.push({
                        callback(result) {
                            observer.next(result);
                            observer.complete();
                        },
                        errorHandler(message, exception) {
                            observer.error({ message, exception });
                            console.error(message, exception);
                            observer.complete();
                        }
                    });
                    service[methodName].apply(service, args);
                });
            });
        });
}

function loadDwrIfNeeded(configuration: BrokerConfiguration): Promise<void> {
    return new Promise<void>(resolve => {
        if ((window as any).dwr) {
            shimDwrThenResolve(resolve);
        } else {
            const path = `${configuration.path}/engine.js`;
            const tag: HTMLScriptElement = document.createElement('script');
            tag.src = path;
            tag.type = 'text/javascript';
            tag.onload = () => shimDwrThenResolve(resolve);
            tag.onerror = () => shimDwrThenResolve(resolve);
            document.body.appendChild(tag);
        }
    });
}

function shimDwrThenResolve(resolve: Function) {
    (function(dwr) {
        const original = dwr.engine.serialize.convert;
        dwr.engine.serialize.convert = function(batch, directrefmap, otherrefmap, data, name, depth) {
            if(data != null && typeof data == "object" && Object.prototype.toString.call(data) == "[object Date]") {
                const offset = data.getTimezoneOffset()*60*1000;
                const newDate = new Date(data.getTime() - offset);
                original(batch, directrefmap, otherrefmap, newDate, name, depth);
            } else {
                original(batch, directrefmap, otherrefmap, data, name, depth);
            }
        }
    })(window['dwr']);
    resolve();
}

function loadServiceIfNeeded(configuration: BrokerConfiguration, name: string): Promise<any> {
    return new Promise<any>(resolve => {
        if ((window as any)[name]) {
            resolve((window as any)[name]);
        } else {
            const path = `${configuration.path}/interface/${name}.js`;
            const tag: HTMLScriptElement = document.createElement('script');
            tag.src = path;
            tag.type = 'text/javascript';
            tag.onload = () => resolve((window as any)[name]);
            tag.onerror = () => resolve();
            document.body.appendChild(tag);
        }
    });
}