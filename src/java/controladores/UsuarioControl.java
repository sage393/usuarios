/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelos.UsuariosFacade;

/**
 *
 * @author sam
 */
@ManagedBean(name = "usuarioControl")
@SessionScoped
public class UsuarioControl implements Serializable {

    @EJB
    UsuariosFacade usuariosFacade;
    
    private Usuarios usuarioSelected;
            
    private List<Usuarios> lstUsuarios;

    @PostConstruct
    public void init() {
        usuarioSelected = new Usuarios();
        listarUsuarios();
    }

    public List<Usuarios> listarUsuarios() {
        lstUsuarios = usuariosFacade.findAll();
        return lstUsuarios;
    }
    
    public void guardarUsuario() {
        try {
            usuariosFacade.edit(usuarioSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "REGISTRO GUARDADO", "PrimeFaces Rocks."));
            
            usuarioSelected = new Usuarios();
            listarUsuarios();
            
            System.out.println("Resgistro guardado");
        } catch (Exception e) {
            System.out.println("Error al guardar registro: " + e);
        }
    }
    
    public void borrarUsuario() {
        usuariosFacade.remove(usuarioSelected);
        listarUsuarios();
    }

    public UsuariosFacade getUsuariosFacade() {
        return usuariosFacade;
    }

    public void setUsuariosFacade(UsuariosFacade usuariosFacade) {
        this.usuariosFacade = usuariosFacade;
    }

    public List<Usuarios> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(List<Usuarios> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    public Usuarios getUsuarioSelected() {
        return usuarioSelected;
    }

    public void setUsuarioSelected(Usuarios usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
    }
    
}
