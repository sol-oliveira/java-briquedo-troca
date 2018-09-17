package brinquedotroca.servlets;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import brinquedotroca.entities.Brinquedo;
import brinquedotroca.entities.Usuario;
import brinquedotroca.repository.BrinquedoRepository;
import brinquedotroca.repository.UsuarioRepository;

@WebServlet("/UploadBrinquedo")
@MultipartConfig
public class UploadBrinquedo extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	
	   public UploadBrinquedo() {
	        super();
	    }
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			Part filePart = request.getPart("file");
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			InputStream fileContent = filePart.getInputStream();
			System.out.println(fileName);
			PrintWriter out = response.getWriter();			
			byte[] outFinal = new byte[(int) filePart.getSize()];			
			IOUtils.readFully(fileContent, outFinal);
			
			/**
			 * Esse outFinal é o array de bytes pra passar pro repository salvar no banco como blob.
			 */
			
//		   FacesContext facesContext = FacesContext.getCurrentInstance();
//		   HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		   int idUsuario = Integer.parseInt(request.getParameter("teste"));      
		   
			EntityManagerFactory factory =	Persistence.createEntityManagerFactory("brinquedotroca");
			
			EntityManager manager = factory.createEntityManager();
			
			manager.getTransaction().begin();
			
			BrinquedoRepository brinquedorepository = new BrinquedoRepository(manager);	
			
			UsuarioRepository usuarioRepository = new UsuarioRepository(manager);			
			   
			Usuario usuario =  usuarioRepository.buscaIdUsuario(idUsuario);
	    		
			Brinquedo brinquedo = new Brinquedo();				
			
			brinquedo.setDescricao(request.getParameter("descricao").toString());
			
	        brinquedo.setUsuario(usuario);			
		    
		    brinquedo.setFoto(outFinal);	    
		
			brinquedorepository.adicionaBrinquedo(brinquedo);	
				
			manager.getTransaction().commit();
			manager.close();
			factory.close();			
			response.sendRedirect("brinquedoenviado.xhtml");

		}
}
