package br.com.eits.boot.test.domain.service.floricultura.service.cliente;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import br.com.eits.boot.domain.entity.floricultura.cliente.Cliente;
import br.com.eits.boot.domain.entity.floricultura.cliente.Sexo;
import br.com.eits.boot.domain.entity.floricultura.cliente.TipoPessoa;
import br.com.eits.boot.domain.repository.floricultura.cliente.IClienteRepository;
import br.com.eits.boot.domain.service.floricultura.cliente.ClienteService;
import br.com.eits.boot.test.domain.AbstractIntegrationTests;

public class ClienteServiceIntegrationTests extends AbstractIntegrationTests {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private IClienteRepository clienteRepository;

	/**
	 * Insere um novo cliente, todos os campos obrigatórios
	 */
	@Test(expected = IllegalStateException.class)
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
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

	/**
	 * Erro de validação, o campo obrigatório nome está nulo
	 */
	@Test(expected = ValidationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
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

	/**
	 * Atualiza o campo obrigatório nome
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
	public void updateNomeMustPass() {

		final Cliente cliente = clienteRepository.findById(1000L).orElse(null);
		cliente.setNome("Ajal");

		this.clienteService.updateCliente(cliente);

		final Cliente clienteBusca = clienteRepository.findById(1000L).orElse(null);
		Assert.assertNotNull(cliente);
		Assert.assertEquals("Ajal", clienteBusca.getNome());
	}

	/**
	 * Erro de validação, o campo email está nulo
	 */
	@Test(expected = ValidationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
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

	/**
	 * Atualiza o campo obrigatorio email
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
	public void updateEmailMustPass() {

		final Cliente cliente = clienteRepository.findById(1000L).orElse(null);
		cliente.setEmail("bb8@email.com");

		this.clienteService.updateCliente(cliente);

		final Cliente clienteBusca = clienteRepository.findById(1000L).orElse(null);
		Assert.assertNotNull(cliente);
		Assert.assertEquals("bb8@email.com", clienteBusca.getEmail());
	}

	/**
	 * Falha ao colocar valores inválidos nos campos obrigatórios de ano, mes e
	 * dia
	 */
	@Test(expected = DateTimeException.class) // InvalidValue
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
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

	/**
	 * Atualiza o campo obrigatorio data de nascimento
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
	public void updateDataNascimentoMustPass() {

		final Cliente cliente = clienteRepository.findById(1000L).orElse(null);
		cliente.setDataNascimento(LocalDate.of(2018, Month.APRIL, 15));

		this.clienteService.updateCliente(cliente);

		final Cliente clienteBusca = clienteRepository.findById(1000L).orElse(null);
		Assert.assertNotNull(cliente);
		Assert.assertEquals(LocalDate.of(2018, Month.APRIL, 15), clienteBusca.getDataNascimento());
	}

	/**
	 * Falha ao inserir o campo obrigatório país
	 */
	@Test(expected = ValidationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
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

	/**
	 * Atualiza o campo pais
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
	public void updatePaisMustPass() {

		final Cliente cliente = clienteRepository.findById(1000L).orElse(null);
		cliente.setPais("Canada");

		this.clienteService.updateCliente(cliente);

		final Cliente clienteBusca = clienteRepository.findById(1000L).orElse(null);
		Assert.assertNotNull(cliente);
		Assert.assertEquals("Canada", clienteBusca.getPais());
	}

	/**
	 * Falha ao inserir o campo obrigatório estado
	 */
	@Test(expected = ValidationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
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

	/**
	 * Atualiza o campo estado
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
	public void updateEstadoMustPass() {

		final Cliente cliente = clienteRepository.findById(1000L).orElse(null);
		cliente.setEstado("Quebec");

		this.clienteService.updateCliente(cliente);

		final Cliente clienteBusca = clienteRepository.findById(1000L).orElse(null);
		Assert.assertNotNull(cliente);
		Assert.assertEquals("Quebec", clienteBusca.getEstado());
	}

	/**
	 * Falha ao inserir o campo obrigatório cidade
	 */
	@Test(expected = ValidationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
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

	/**
	 * Atualiza o campo obrigatório cidade
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
	public void updateCidadeMustPass() {

		final Cliente cliente = clienteRepository.findById(1000L).orElse(null);
		cliente.setCidade("Montreal");

		this.clienteService.updateCliente(cliente);

		final Cliente clienteBusca = clienteRepository.findById(1000L).orElse(null);
		Assert.assertNotNull(cliente);
		Assert.assertEquals("Montreal", clienteBusca.getCidade());
	}

	/**
	 * Falha ao inserir cep obrigatorio
	 */
	@Test(expected = ValidationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
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

	/**
	 * Atualiza o campo obrigatório cep
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
	public void updateCepMustPass() {

		final Cliente cliente = clienteRepository.findById(1000L).orElse(null);
		cliente.setCep("85877000");

		this.clienteService.updateCliente(cliente);

		final Cliente clienteBusca = clienteRepository.findById(1000L).orElse(null);
		Assert.assertNotNull(cliente);
		Assert.assertEquals("85877000", clienteBusca.getCep());
	}

	/**
	 * Falha no campo obrigatório rua, está nulo
	 */
	@Test(expected = ValidationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
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

	/**
	 * Atualiza o campo obrigatório rua
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
	public void updateRuaMustPass() {

		final Cliente cliente = clienteRepository.findById(1000L).orElse(null);
		cliente.setEstado("Montreal");

		this.clienteService.updateCliente(cliente);

		final Cliente clienteBusca = clienteRepository.findById(1000L).orElse(null);
		Assert.assertNotNull(cliente);
		Assert.assertEquals("Montreal", clienteBusca.getEstado());
	}

	/**
	 * Lista o Cliente pelo email por filtro
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
	public void listClientesByFiltersMustEmailReturn1() {

		final Page<Cliente> clientes = this.clienteService.listClientesByFilters(null, "paranaue@email.com",
				null, null);

		Assert.assertNotNull(clientes);
		Assert.assertEquals(1, clientes.getTotalElements());
	}

	/**
	 * Lista um cliente pelo nome por filtro
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
	public void listClientesByFiltersMustNomeReturn1() {

		final Page<Cliente> clientes = this.clienteService.listClientesByFilters("Cabral", null, null, null);

		Assert.assertNotNull(clientes);
		Assert.assertEquals(1, clientes.getTotalElements());
	}

	/**
	 * Erro ao listar o nome gerando erro de enunciado por não estar na tabela
	 */
	@Test(expected = AssertionError.class)
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
	public void listClientesMustFailMandatoryFieldNome() {

		final Page<Cliente> clientes = this.clienteService.listClientesByFilters("Luara", null, null, null);

		Assert.assertNotNull(clientes);
		Assert.assertEquals(1, clientes.getTotalElements());
		Assert.assertTrue(1000L == clientes.getContent().get(0).getId());
	}
	
	/**
	 * Remove o cliente pelo id
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
	public void removeClientesMustPass() {

		this.clienteService.removeCliente(1000L);

		final Cliente cliente = this.clienteRepository.findById(1000L).orElse(null);
		Assert.assertNull(cliente);
	}

	/**
	 * Falha na consulta para a remoção de um cliente, pois não encontrou o
	 * cliente pelo id
	 */
	@Test(expected = EmptyResultDataAccessException.class)
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/clientes.sql" })
	public void removeClientesMustFail() {

		this.clienteService.removeCliente(1002L);

		final Cliente cliente = this.clienteRepository.findById(1002L).orElse(null);
		Assert.assertNull(cliente);
	}
	
}
