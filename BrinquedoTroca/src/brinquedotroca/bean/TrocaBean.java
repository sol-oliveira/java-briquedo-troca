package brinquedotroca.bean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import brinquedotroca.entities.Troca;
import brinquedotroca.entities.Usuario;
import brinquedotroca.entities.Brinquedo;
import brinquedotroca.repository.TrocaRepository;
import brinquedotroca.repository.BrinquedoRepository;

@ManagedBean
public class TrocaBean {

	private int destinario;
	private int remetente;
	private List<Troca> listMinhaTroca = new ArrayList<>();	
	private UploadedFile fotoRemetente;
	private UploadedFile fotoDestinatario;

	public UploadedFile getFotoRemetente() {
		return fotoRemetente;
	}

	public void setFotoRemetente(UploadedFile fotoRemetente) {
		this.fotoRemetente = fotoRemetente;
	}

	public UploadedFile getFotoDestinatario() {
		return fotoDestinatario;
	}

	public void setFotoDestinatario(UploadedFile fotoDestinatario) {
		this.fotoDestinatario = fotoDestinatario;
	}


	public int getDestinario() {
		return destinario;
	}

	public void setDestinario(int destinario) {
		this.destinario = destinario;
	}

	public int getRemetente() {
		return remetente;
	}

	public void setRemetente(int remetente) {
		this.remetente = remetente;
	}

	public List<Troca> getListMinhaTroca() {
		this.buscaTodos();
		return listMinhaTroca;
	}

	public void setListMinhaTroca(List<Troca> listMinhaTroca) {
		this.listMinhaTroca = listMinhaTroca;
	}

	public TrocaBean() {    

	}

	public String submit(int remetenteId, int destinatarioId ) throws IOException {

		EntityManagerFactory factory = 	Persistence.createEntityManagerFactory("brinquedotroca");			
		EntityManager manager = factory.createEntityManager();			
		manager.getTransaction().begin();
		TrocaRepository trocarepository = new TrocaRepository(manager);

		BrinquedoRepository toyrepository = new BrinquedoRepository(manager);
		Brinquedo remetente = toyrepository.buscaId(remetenteId) ;	
		Brinquedo destinatario = toyrepository.buscaId(destinatarioId);			

		Troca troca = new Troca();
		troca.setRemetente(remetente);	
		troca.setDestinatario(destinatario); 
		trocarepository.adiciona(troca);

		manager.getTransaction().commit();
		manager.close();
		factory.close();
		return "sugestaoenviada";

	}

	public void buscaTodos() {

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			Usuario u = (Usuario) session.getAttribute("usuario");

			EntityManagerFactory factory = Persistence.createEntityManagerFactory("brinquedotroca");
			EntityManager manager = factory.createEntityManager();
			TrocaRepository trocarepository = new TrocaRepository(manager);
			manager.getTransaction().begin();

			List<Troca> troca = trocarepository.buscaMinhaTroca(u.getIdUsuario());
			manager.close();

			for(Troca t: troca) {
				listMinhaTroca.add(t);
			}

			System.out.println("TESTE PARA TAMANHO DO RETORNO " + troca.size() + " - usuario" + u.getIdUsuario());

		}catch (Exception e) {
			System.out.println("Erro " + e.getMessage());
			e.printStackTrace();
		}
	}

}
