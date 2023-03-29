package io.github.guisso.gruposestudo;

import java.io.IOException;
import java.io.PrintWriter;
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
            
            // Cadastros
            
            // Pessoas
            
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PessoaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PessoaServlet at " + request.getContextPath() + "</h1>");
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
