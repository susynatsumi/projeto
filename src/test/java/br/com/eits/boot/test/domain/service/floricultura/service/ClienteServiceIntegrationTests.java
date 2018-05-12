package br.com.eits.boot.test.domain.service.floricultura.service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import br.com.eits.boot.domain.entity.floricultura.cliente.Cliente;
import br.com.eits.boot.domain.entity.floricultura.cliente.Sexo;
import br.com.eits.boot.domain.entity.floricultura.cliente.TipoPessoa;
import br.com.eits.boot.domain.repository.floricultura.IClienteRepository;
import br.com.eits.boot.domain.service.floricultura.ClienteService;
import br.com.eits.boot.test.domain.AbstractIntegrationTests;

public class ClienteServiceIntegrationTests extends AbstractIntegrationTests {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	//TODO Insere cliente em uma nova tabela
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql" //caminho table
	})
	public void insertClienteMustPass() {
	
		final Cliente cliente = new Cliente();
		cliente.setNome("Qualquer Coisa");
		cliente.setEmail("edi@email.com");
		cliente.setDataNascimento(LocalDate.of(2016, Month.APRIL, 9));
		cliente.setPais("Paraguai");
		cliente.setEstado("Buenas");
		cliente.setCidade("Missal");
		cliente.setCep("8589000");
		cliente.setRua("Jose de abreu");
		cliente.setSexo(Sexo.FEMININO);
		cliente.setTipoPessoa(TipoPessoa.PESSOA_FISICA);
		this.clienteService.insertCliente(cliente);
		
		Assert.assertNotNull(cliente);
		Assert.assertNotNull(cliente.getId());
		
	}	

	@Test(expected = ValidationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql" 
	})
	public void insertClienteMustFailMandatoryFieldNome() {
	
		final Cliente cliente = new Cliente();
		cliente.setNome(null);
		cliente.setEmail("edi@outlook.com");
		cliente.setDataNascimento(LocalDate.of(2016, Month.APRIL, 9));
		cliente.setPais("Paraguai");
		cliente.setEstado("Buenas");
		cliente.setCidade("Missal");
		cliente.setCep("8589000");
		cliente.setRua("Jose de abreu");
		cliente.setSexo(Sexo.FEMININO);
		cliente.setTipoPessoa(TipoPessoa.PESSOA_FISICA);
		this.clienteService.insertCliente(cliente);
				
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void updateNomeMustPass() {
	
		final Cliente cliente = clienteRepository.findById(1000L).orElse(null);
		cliente.setNome("Ajal");

		this.clienteService.updateCliente(cliente);
		
		final Cliente clienteBusca = clienteRepository.findById(1000L).orElse(null);
		Assert.assertNotNull(cliente);
		Assert.assertEquals("Ajal", clienteBusca.getNome());
	
	}
	
	@Test(expected = ValidationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void insertClienteMustFailMandatoryFieldEmail() {
	
		final Cliente cliente = new Cliente();
		cliente.setNome("Qualquer coisa");
		cliente.setEmail(null);
		cliente.setDataNascimento(LocalDate.of(2016, Month.APRIL, 9));
		cliente.setPais("Paraguai");
		cliente.setEstado("Buenas");
		cliente.setCidade("Missal");
		cliente.setCep("8589000");
		cliente.setRua("Jose de abreu");
		cliente.setSexo(Sexo.FEMININO);
		cliente.setTipoPessoa(TipoPessoa.PESSOA_FISICA);
		this.clienteService.insertCliente(cliente);
				
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql" 
	})
	public void updateEmailMustPass() {
	
		final Cliente cliente = clienteRepository.findById(1000L).orElse(null);
		cliente.setEmail("bb8@email.com");

		this.clienteService.updateCliente(cliente);
		
		final Cliente clienteBusca = clienteRepository.findById(1000L).orElse(null);
		Assert.assertNotNull(cliente);
		Assert.assertEquals("bb8@email.com", clienteBusca.getEmail());
	
	}
	
    @Test(expected = DateTimeException.class) //InvalidValue
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void insertClienteMustFailMandatoryFieldDataNascimento() {
	
		final Cliente cliente = new Cliente();
		cliente.setNome("Qualquer Coisa");
		cliente.setEmail("edi@email.com");
		cliente.setDataNascimento(LocalDate.of(0, 0, 0));
		cliente.setPais("Paraguai");
		cliente.setEstado("Buenas");
		cliente.setCidade("Missal");
		cliente.setCep("8589000");
		cliente.setRua("Jose de abreu");
		cliente.setSexo(Sexo.FEMININO);
		cliente.setTipoPessoa(TipoPessoa.PESSOA_FISICA);
		this.clienteService.insertCliente(cliente);
		
		Assert.assertNotNull(cliente);
		Assert.assertNotNull(cliente.getId());
		
	}
    
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void updateDataNascimentoMustPass() {
		
		final Cliente cliente = clienteRepository.findById(1000L).orElse(null);
		cliente.setDataNascimento(LocalDate.of(2018, Month.APRIL, 15));
		
		this.clienteService.updateCliente(cliente);
		
		final Cliente clienteBusca = clienteRepository.findById(1000L).orElse(null);
		Assert.assertNotNull(cliente);
		Assert.assertEquals(LocalDate.of(2018, Month.APRIL, 15), clienteBusca.getDataNascimento());
		
	}
	
	@Test(expected = ValidationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void insertClienteMustFailMandatoryFieldPais() {
	
		final Cliente cliente = new Cliente();
		cliente.setNome("Qualquer Coisa");
		cliente.setEmail("edi@email.com");
		cliente.setDataNascimento(LocalDate.of(2016, Month.APRIL, 9));
		cliente.setPais(null);
		cliente.setEstado("Buenas");
		cliente.setCidade("Missal");
		cliente.setCep("8589000");
		cliente.setRua("Jose de abreu");
		cliente.setSexo(Sexo.FEMININO);
		cliente.setTipoPessoa(TipoPessoa.PESSOA_FISICA);
		this.clienteService.insertCliente(cliente);
		
		Assert.assertNotNull(cliente);
		Assert.assertNotNull(cliente.getId());
		
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void updatePaisMustPass() {
		
		final Cliente cliente = clienteRepository.findById(1000L).orElse(null);
		cliente.setPais("Canada");
		
		this.clienteService.updateCliente(cliente);
		
		final Cliente clienteBusca = clienteRepository.findById(1000L).orElse(null);
		Assert.assertNotNull(cliente);
		Assert.assertEquals("Canada", clienteBusca.getPais());
		
	}
	
	@Test(expected = ValidationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void insertClienteMustFailMandatoryFieldEstado() {
	
		final Cliente cliente = new Cliente();
		cliente.setNome("Qualquer Coisa");
		cliente.setEmail("edi@email.com");
		cliente.setDataNascimento(LocalDate.of(2016, Month.APRIL, 9));
		cliente.setPais("Paraguai");
		cliente.setEstado(null);
		cliente.setCidade("Missal");
		cliente.setCep("8589000");
		cliente.setRua("Jose de abreu");
		cliente.setSexo(Sexo.FEMININO);
		cliente.setTipoPessoa(TipoPessoa.PESSOA_FISICA);
		this.clienteService.insertCliente(cliente);
		
		Assert.assertNotNull(cliente);
		Assert.assertNotNull(cliente.getId());
		
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void updateEstadoMustPass() {
		
		final Cliente cliente = clienteRepository.findById(1000L).orElse(null);
		cliente.setEstado("Quebec");
		
		this.clienteService.updateCliente(cliente);
		
		final Cliente clienteBusca = clienteRepository.findById(1000L).orElse(null);
		Assert.assertNotNull(cliente);
		Assert.assertEquals("Quebec", clienteBusca.getEstado());
		
	} 
	
	@Test(expected = ValidationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void insertClienteMustFailMandatoryFieldCidade() {
	
		final Cliente cliente = new Cliente();
		cliente.setNome("Qualquer Coisa");
		cliente.setEmail("edi@email.com");
		cliente.setDataNascimento(LocalDate.of(2016, Month.APRIL, 9));
		cliente.setPais("Paraguai");
		cliente.setEstado("Buenas");
		cliente.setCidade(null);
		cliente.setCep("8589000");
		cliente.setRua("Jose de abreu");
		cliente.setSexo(Sexo.FEMININO);
		cliente.setTipoPessoa(TipoPessoa.PESSOA_FISICA);
		this.clienteService.insertCliente(cliente);
		
		Assert.assertNotNull(cliente);
		Assert.assertNotNull(cliente.getId());
		
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void updateCidadeMustPass() {
		
		final Cliente cliente = clienteRepository.findById(1000L).orElse(null);
		cliente.setCidade("Montreal");
		
		this.clienteService.updateCliente(cliente);
		
		final Cliente clienteBusca = clienteRepository.findById(1000L).orElse(null);
		Assert.assertNotNull(cliente);
		Assert.assertEquals("Montreal", clienteBusca.getCidade());
		
	} 
	
	@Test(expected = ValidationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void insertClienteMustFailMandatoryFieldCep() {
	
		final Cliente cliente = new Cliente();
		cliente.setNome("Qualquer Coisa");
		cliente.setEmail("edi@email.com");
		cliente.setDataNascimento(LocalDate.of(2016, Month.APRIL, 9));
		cliente.setPais("Paraguai");
		cliente.setEstado("Buenas");
		cliente.setCidade("Missal");
		cliente.setCep(null);
		cliente.setRua("Jose de abreu");
		cliente.setSexo(Sexo.FEMININO);
		cliente.setTipoPessoa(TipoPessoa.PESSOA_FISICA);
		this.clienteService.insertCliente(cliente);
		
		Assert.assertNotNull(cliente);
		Assert.assertNotNull(cliente.getId());
		
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void updateCepMustPass() {
		
		final Cliente cliente = clienteRepository.findById(1000L).orElse(null);
		cliente.setCep("85877000");
		
		this.clienteService.updateCliente(cliente);
		
		final Cliente clienteBusca = clienteRepository.findById(1000L).orElse(null);
		Assert.assertNotNull(cliente);
	Assert.assertEquals("85877000", clienteBusca.getCep());
		
	}
	
	@Test(expected = ValidationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void insertClienteMustFailMandatoryFieldRua() {
	
		final Cliente cliente = new Cliente();
		cliente.setNome("Qualquer Coisa");
		cliente.setEmail("edi@email.com");
		cliente.setDataNascimento(LocalDate.of(2016, Month.APRIL, 9));
		cliente.setPais("Paraguai");
		cliente.setEstado("Buenas");
		cliente.setCidade("Missal");
		cliente.setCep("8589000");
		cliente.setRua(null);
		cliente.setSexo(Sexo.FEMININO);
		cliente.setTipoPessoa(TipoPessoa.PESSOA_FISICA);
		this.clienteService.insertCliente(cliente);
		
		Assert.assertNotNull(cliente);
		Assert.assertNotNull(cliente.getId());
		
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void updateRuaMustPass() {
		
		final Cliente cliente = clienteRepository.findById(1000L).orElse(null);
		cliente.setEstado("Montreal");
		
		this.clienteService.updateCliente(cliente);
		
		final Cliente clienteBusca = clienteRepository.findById(1000L).orElse(null);
		Assert.assertNotNull(cliente);
		Assert.assertEquals("Montreal", clienteBusca.getEstado());
		
	} 
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void listClientesMustPassReturnUm() {
		
		final Page<Cliente> clientes = this.clienteService.listClientesByFilters(
				null, 
				null, 
				null,
				null);
		
		Assert.assertNotNull(clientes);
		Assert.assertEquals(2L, clientes.getTotalElements());
		
	}
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void listClientesMustPassNome() {
		
		final Page<Cliente> clientes = this.clienteService.listClientesByFilters(
				"Cabral", 
				null, 
				null,
				null);
		
		Assert.assertNotNull(clientes);
		Assert.assertEquals(2L, clientes.getTotalElements());
		Assert.assertTrue(1000L == clientes.getContent().get(0).getId());
		
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void RemoveClienteMustPass() {
		
		this.clienteService.removeCliente(1000L);
		
		final Cliente cliente = this.clienteRepository.findById(1000L).orElse(null);
		Assert.assertNull(cliente);
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql"
	})
	public void RemoveClienteMustFail() {
		
		this.clienteService.removeCliente(1000L);

	}
}
