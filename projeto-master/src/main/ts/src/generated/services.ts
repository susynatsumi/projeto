import { Inject, Injectable } from '@angular/core';
import { BROKER_CONFIGURATION, BrokerConfiguration, dwrWrapper } from './services-wrapper';
import { Observable } from 'rxjs/Observable';
import { PageRequest, SortDirection, Pageable, NullHandling, Sort, SortOrder, Page } from './entities';

