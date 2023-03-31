package io.github.guisso.gruposestudo;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

/**
 *
 * @author Luis Guisso &lt;luis.guisso at ifnmg.edu.br&gt;
 */
@Transactional
@WebServlet(name = "PessoaServlet", urlPatterns = {"/PessoaServlet"})
public class PessoaServlet extends HttpServlet {

    @Inject
    private PessoaServiceLocal pessoaService;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            // Enderecos
            Endereco e1 = new Endereco();
            e1.setLogradouro("Rua Um");
            e1.setBairro("A");
            e1.setNumero(1001);

            Endereco e2 = new Endereco();
            e2.setLogradouro("Rua Dois");
            e2.setBairro("A");
            e2.setNumero(99);

            Endereco e3 = new Endereco();
            e3.setLogradouro("Rua Três");
            e3.setBairro("B");
            e3.setNumero(55555);

            // Telefones
            Telefone p1t1 = new Telefone();
            p1t1.setNumero(1188888888L);

            Telefone p1t2 = new Telefone();
            p1t2.setNumero(2288888888L);

            Telefone p1t3 = new Telefone();
            p1t3.setNumero(3388888888L);

            Telefone p2t1 = new Telefone();
            p2t1.setNumero(4488888888L);

            Telefone p2t2 = new Telefone();
            p2t2.setNumero(5588888888L);

            // Grupos
            Grupo g1 = new Grupo();
            g1.setNome("Grupo Trabalho 1");
            g1.setAtivo(true);

            Grupo g2 = new Grupo();
            g2.setNome("Grupo Trabalho 2");
            g2.setAtivo(false);

            // Cadastros
            Cadastro p1g1 = new Cadastro();
            p1g1.setAtivo(true);
            p1g1.setDataHoraCadastro(LocalDateTime.of(2023, 3, 22, 12, 0));
            p1g1.setGrupo(g1);

            Cadastro p1g2 = new Cadastro();
            p1g2.setAtivo(true);
            p1g2.setDataHoraCadastro(LocalDateTime.of(2023, 2, 22, 12, 0));
            p1g2.setGrupo(g2);

            Cadastro p2g2 = new Cadastro();
            p2g2.setAtivo(true);
            p2g2.setDataHoraCadastro(LocalDateTime.of(2023, 3, 22, 12, 0));
            p2g2.setGrupo(g2);

            // Pessoas
            Pessoa p1 = new Pessoa();
            p1.setNome("Ana");
            p1.setNascimento(LocalDate.of(2000, 3, 1));

            p1.setEndereco(e1);

            p1.getTelefones().add(p1t1);
            p1.getTelefones().add(p1t2);
            p1.getTelefones().add(p1t3);

            p1.getCadastros().add(p1g1);
            p1.getCadastros().add(p1g2);

            Pessoa p2 = new Pessoa();
            p2.setNome("Beatriz");
            p2.setNascimento(LocalDate.of(1990, 3, 1));

            p2.setEndereco(e2);

            p2.getTelefones().add(p2t1);
            p2.getTelefones().add(p2t2);

            p2.getCadastros().add(p2g2);

            // Salvamento via beans de sessão
            pessoaService.salvar(p1);
            pessoaService.salvar(p2);

            // Testes com JPQL
            // Buscar todas as pessoas
            List<Pessoa> todasPessoas
                    = pessoaService.buscarTodasPessoas();

            // Buscara nomes e idades
            List<Object[]> nomesIdadesPessoas
                    = pessoaService.buscarNomesIdades();

            //Buscar Pessoas com mais que 25 anos
            List<Pessoa> pessoasMaiores25
                    = pessoaService.localizarMaiores25();

            //Buscar Pessoas com mais que 15 anos PARAMETRIZADO
            List<Pessoa> pessoasMaiores15
                    = pessoaService.localizarMaioresQue((short) 15);
            
            //Buscar Pessoas entre 15 e 30 anos PARAMETRIZADO
            List<Pessoa> pessoasEntre15E30
                    = pessoaService.localizarFaixaIdade((short) 15, (short) 30);
            
            //Buscar Pessoas entre 20 e 50 anos PARAMETRIZADO
            List<Pessoa> pessoasEntre20E50
                    = pessoaService.localizarFaixaIdade((short) 20, (short) 50);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PessoaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>JPQL</h1>");

            out.println("<h2>Consulta simples</h2>");
            out.println("<ul>");
            for (Pessoa pessoa : todasPessoas) {
                out.println("<li>" + pessoa + "</li>");
            }
            out.println("</ul>");

            out.println("<h2>Projeção (Object[])</h2>");
            out.println("<ul>");
            for (Object[] o : nomesIdadesPessoas) {
                out.println("<li>" + o[0] + "," + o[1] + "</li>");
            }
            out.println("</ul>");

            out.println("<h2>Cláusula WHERE (&gt; 25)</h2>");
            out.println("<ul>");
            for (Pessoa p : pessoasMaiores25) {
                out.println("<li>" + p.getId()
                        + ", " + p.getNome()
                        + ", " + p.getIdade()
                        + "</li>");
            }
            out.println("</ul>");

            out.println("<h2>Parametrizada (&gt; 15)</h2>");
            out.println("<ul>");
            for (Pessoa p : pessoasMaiores15) {
                out.println("<li>" + p.getId()
                        + ", " + p.getNome()
                        + ", " + p.getIdade()
                        + "</li>");
            }
            out.println("</ul>");
            
            out.println("<h2>Parametrizada (&gt;= 15 AND &lt;= 30)</h2>");
            out.println("<ul>");
            for (Pessoa p : pessoasEntre15E30) {
                out.println("<li>" + p.getId()
                        + ", " + p.getNome()
                        + ", " + p.getIdade()
                        + "</li>");
            }
            out.println("</ul>");
            
            out.println("<h2>Parametrizada (&gt;= 20 AND &lt;= 50)</h2>");
            out.println("<ul>");
            for (Pessoa p : pessoasEntre20E50) {
                out.println("<li>" + p.getId()
                        + ", " + p.getNome()
                        + ", " + p.getIdade()
                        + "</li>");
            }
            out.println("</ul>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
