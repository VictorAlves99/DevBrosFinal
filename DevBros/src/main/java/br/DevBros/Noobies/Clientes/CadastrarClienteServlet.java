package br.DevBros.Noobies.Clientes;

import br.DevBros.Noobies.Clientes.ClienteDAO;
import br.DevBros.Noobies.Clientes.Cliente;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabriela.vsmarques
 */
@WebServlet(name = "CadastrarClienteServlet", urlPatterns = {"/CadastrarCliente"})
public class CadastrarClienteServlet extends HttpServlet {

    protected void incluirCliente(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cliente cliente = new Cliente();

        // RECUPERA INFORMACOES DA REQUISICAO
        cliente.setNome(request.getParameter("nome")); 
        cliente.setCpf(request.getParameter("cpf"));               
        cliente.setTelefone(request.getParameter("Telefone"));
        cliente.setEmail(request.getParameter("email"));

        boolean linhasAfetadas = ClienteDAO.incluirCliente(cliente);

        if (linhasAfetadas) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/consultarclientes");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrar-clientes.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        incluirCliente("GET",request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        incluirCliente("POST", request, response);

    }
}
