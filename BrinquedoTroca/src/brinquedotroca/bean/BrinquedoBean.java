package brinquedotroca.bean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import brinquedotroca.entities.Brinquedo;
import brinquedotroca.entities.Usuario;
import brinquedotroca.repository.BrinquedoRepository;

@ManagedBean
public class BrinquedoBean {

	private UploadedFile foto;
	private String descricao;
	private  int idUsuario;	
	private List<Brinquedo> listtoy = new ArrayList<Brinquedo>();
	private List<Brinquedo> outroslisttoy = new ArrayList<Brinquedo>();

	public UploadedFile getFoto() {
		return foto;
	}

	public void setFoto(UploadedFile foto) {
		this.foto = foto;
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<Brinquedo> getListtoy() {
		return listtoy;
	}

	public void setListtoy(List<Brinquedo> listtoy) {
		this.listtoy = listtoy;
	}	
	public List<Brinquedo> getOutroslisttoy() {
		this.buscaTodos();
		return outroslisttoy;
	}

	public void setOutroslisttoy(List<Brinquedo> outroslisttoy) {
		this.outroslisttoy = outroslisttoy;
	}

	public BrinquedoBean() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		Usuario u = (Usuario) session.getAttribute("usuario"); 

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("brinquedotroca");
		EntityManager manager = factory.createEntityManager();
		BrinquedoRepository toyRepository = new BrinquedoRepository(manager);
		manager.getTransaction().begin();

		List<Brinquedo> brinquedo = toyRepository.busca(u.getIdUsuario());
		manager.close();

		for(Brinquedo t : brinquedo) { 
			geraImagemServidor(t, "/resources/imagens/");
			t.setNomeFoto("/resources/imagens/" + t.getId() + ".JPG");
			listtoy.add(t);
		} 
	}


	public void adiciona() throws IOException {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		Usuario u = (Usuario) session.getAttribute("usuario"); 
		u.getIdUsuario();

		byte[] bytes = new byte[0];

		if(foto != null) bytes = foto.getBytes();		

		EntityManagerFactory factory = 	Persistence.createEntityManagerFactory("brinquedotroca");			
		EntityManager manager = factory.createEntityManager();			
		manager.getTransaction().begin();			
		BrinquedoRepository toyRepository = new BrinquedoRepository(manager);	

		Brinquedo brinquedo = new Brinquedo();
		brinquedo.setFoto(bytes);
		brinquedo.setDescricao(descricao);  
		brinquedo.setUsuario(u);

		if(descricao.equals("Escolher descricao") || bytes.length == 0) {
			System.out.println("descricao.trim ->"+ descricao);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(String.format(" Falha no cadastro do brinquedo. Por favor, preencha todos os campos! ")));
		}else {
			toyRepository.adiciona(brinquedo);			
			manager.getTransaction().commit();
			manager.close();
			factory.close();
			System.out.println("descricao ->"+getDescricao());
			System.out.println("descricao1 ->"+ descricao);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(String.format(" Brinquedo cadastrado com sucesso! ")));
		}										
	}



	public void geraImagemServidor(Brinquedo toy, String pathServidor) {
		try {

			if(toy.getFoto() != null) {
				FacesContext context = FacesContext.getCurrentInstance();
				ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
				String path = servletContext.getRealPath(pathServidor);
				File arquivo = new File(path);
				if(!arquivo.exists()) {
					arquivo.createNewFile();
				}

				byte[] bytes = toy.getFoto();
				FileImageOutputStream imageOutput= new FileImageOutputStream(new File(arquivo, toy.getId()+ ".JPG"));
				imageOutput.write(bytes,0,bytes.length);
				imageOutput.flush();
				imageOutput.close();				
			}

		}catch(Exception ex) {
			System.out.println("Erro " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void buscaTodos() {

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			Usuario u = (Usuario) session.getAttribute("usuario"); 

			EntityManagerFactory factory = 	Persistence.createEntityManagerFactory("brinquedotroca");
			EntityManager manager = factory.createEntityManager();
			manager.getTransaction().begin();			
			BrinquedoRepository toyrepository = new BrinquedoRepository(manager);

			List<Brinquedo> toy = toyrepository.buscaTodos(u.getIdUsuario());
			manager.close();

			for(Brinquedo t: toy) {
				geraImagemServidor(t, "/resources/imagens/");
				t.setNomeFoto("/resources/imagens/" + t.getId() + ".JPG");
				outroslisttoy.add(t);
			}
			System.out.println("TESTE PARA TAMANHO DO RETORNO " + toy.size() + " - usuario" + u.getIdUsuario());
		}catch (Exception e) {
			System.out.println("Erro " + e.getMessage());
			e.printStackTrace();
		}
	}


	public String deletarBrinquedo() {

		EntityManagerFactory factory =	Persistence.createEntityManagerFactory("brinquedotroca");				
		EntityManager manager = factory.createEntityManager();				
		manager.getTransaction().begin();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		Usuario u = (Usuario) session.getAttribute("usuario"); 

		BrinquedoRepository brinquedorepository = new BrinquedoRepository(manager);

		brinquedorepository.deletarBrinquedo(u.getIdUsuario());		
		manager.getTransaction().commit();
		manager.close();
		factory.close();

		return "brinquedodeletado";
	}

}