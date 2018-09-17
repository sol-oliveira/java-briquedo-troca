package brinquedotroca.bean;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import brinquedotroca.entities.Brinquedo;
import brinquedotroca.entities.Usuario;
import brinquedotroca.repository.BrinquedoRepository;



@ManagedBean
public class BrinquedoBean {	
	
	private int idBrinquedo;
	private String descricao;
	private  int idUsuario;
	private Part image;
	private List<String> imagens = new ArrayList<String>();
	private List<String> outrasImagens = new ArrayList<String>();
	private List<Brinquedo> brinquedosMostrar = new ArrayList<Brinquedo>();
	
	public List<Brinquedo> getBrinquedosMostrar() {
		return brinquedosMostrar;
	}



	public void setBrinquedosMostrar(List<Brinquedo> brinquedosMostrar) {
		this.brinquedosMostrar = brinquedosMostrar;
	}



	public List<String> getOutrasImagens() {
		this.obterTodos();
		return outrasImagens;
	}



	public void setOutrasImagens(List<String> outrasImagens) {
		this.outrasImagens = outrasImagens;
	}



	public List<Brinquedo> getOutrosBrinquedos() {
		return outrosBrinquedos;
	}



	public void setOutrosBrinquedos(List<Brinquedo> outrosBrinquedos) {
		this.outrosBrinquedos = outrosBrinquedos;
	}

	private List<Brinquedo> outrosBrinquedos;


	public BrinquedoBean() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("brinquedotroca");
		EntityManager manager = factory.createEntityManager();
		BrinquedoRepository brinquedoRepository = new BrinquedoRepository(manager);
		manager.getTransaction().begin();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Usuario u = (Usuario) session.getAttribute("usuario");        

		List<Brinquedo> brinquedos = brinquedoRepository.busca(u.getIdUsuario());
		manager.close();
		for(Brinquedo b : brinquedos) { 
		    byte[] imageData = b.getFoto(); 
		    b.setImagemConvertida(org.apache.commons.codec.binary.Base64.encodeBase64String(imageData));
		    brinquedosMostrar.add(b);
           imagens.add(org.apache.commons.codec.binary.Base64.encodeBase64String(imageData)); 
		}
	}
  	
	
	
/*		public BrinquedoBean(String nome) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("brinquedotroca");
		EntityManager manager = factory.createEntityManager();
		BrinquedoRepository brinquedoRepository = new BrinquedoRepository(manager);
		manager.getTransaction().begin();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Usuario u = (Usuario) session.getAttribute("usuario");        

		List<Brinquedo> brinquedos = brinquedoRepository.busca(u.getIdUsuario());
		manager.close();
		for(Brinquedo b : brinquedos) { 
		    byte[] imageData = b.getFoto(); 
		    imagens.add(org.apache.commons.codec.binary.Base64.encodeBase64String(imageData)); 
		}
	} */
	
	
	public List<String> getImagens() {
		return imagens;
	}

	public void setImagens(List<String> imagens) {
		this.imagens = imagens;
	}

	public int getIdBrinquedo() {
		return idBrinquedo;
	}

	public void setIdBrinquedo(int idBrinquedo) {
		this.idBrinquedo = idBrinquedo;
	}

	public Part getImage() {
		return image;
	}
	public void setImage(Part image) {
		this.image = image;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
		
	public String cadastrarBrinquedo() throws IOException {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Usuario u = (Usuario) session.getAttribute("nome");        
        u.getIdUsuario();
		
               
		EntityManagerFactory factory =	Persistence.createEntityManagerFactory("brinquedotroca");
		
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		
		BrinquedoRepository brinquedorepository = new BrinquedoRepository(manager);
		
		Brinquedo brinquedo = new Brinquedo();	
		
		brinquedo.setDescricao(descricao);
	    brinquedo.setUsuario(u);
   		  
	    InputStream in=image.getInputStream();
	    brinquedo.setFoto(IOUtils.toByteArray(in));	    
	  
	
		brinquedorepository.adicionaBrinquedo(brinquedo);	
			
		manager.getTransaction().commit();
		manager.close();
		factory.close();			
		
		return "brinquedoenviado";
	}
	

	
	
	public String deletarBrinquedo(int id) {
		
		EntityManagerFactory factory =	Persistence.createEntityManagerFactory("brinquedotroca");
		
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		
		BrinquedoRepository brinquedorepository = new BrinquedoRepository(manager);
		
		brinquedorepository.deletarBrinquedo(id);		
		
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		
		return "brinquedodeletado";
	}
	
	public void obterTodos() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("brinquedotroca");
		EntityManager manager = factory.createEntityManager();
		BrinquedoRepository brinquedoRepository = new BrinquedoRepository(manager);
		manager.getTransaction().begin();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Usuario u = (Usuario) session.getAttribute("usuario");        

		List<Brinquedo> brinquedos = brinquedoRepository.buscaTodos(u.getIdUsuario());
		manager.close();
		for(Brinquedo b : brinquedos) { 
		    byte[] imageData = b.getFoto(); 
		    outrasImagens.add(org.apache.commons.codec.binary.Base64.encodeBase64String(imageData)); 
		}
	}
	
	public String sugerirTroca() {
		return "sugestaotroca.xhtml";
	}
}
