package com.example.vinicius.controllers;

import com.example.vinicius.models.Client;
import com.example.vinicius.repository.TClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController{

    @Autowired
    private TClientRepository cr;

    @RequestMapping(value ="/cadClient", method = RequestMethod.GET)
    public String form() {
        return "formCadClient";
    }

    @RequestMapping(value ="/cadClient", method = RequestMethod.POST)
    public String form(Client client) {
        cr.save(client);
        return "redirect:/cadClient";
    }

    @RequestMapping("/clients")
    public ModelAndView listClients() {
        ModelAndView mv = new ModelAndView("index");
        Iterable<Client> clients = cr.findAll();
        mv.addObject("clients", clients);
        return mv;
    }

/*    public String deleteClient(long id){
        Iterable<Client> clients = cr.findAll();
        for(Client client : clients) {
            if(client.getId() == id) {
                cr.delete(client);
            }
        }
        return "redirect:/clients";
    }*/

    @RequestMapping("/deleteClient")
    public String deleteClient(long id){
        Client client = cr.findById(id);
        cr.delete(client);
        return "redirect:/clients";
    }

}