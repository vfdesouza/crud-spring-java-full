package com.example.vinicius.controllers;

import com.example.vinicius.models.Client;
import com.example.vinicius.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController{

    @Autowired
    private ClientRepository cr;


    @RequestMapping(value ="/formInsert", method = RequestMethod.GET)
    public String form() {
        return "formInsert";
    }

    @RequestMapping(value ="/formInsert", method = RequestMethod.POST)
    public String insert(Client client) {
        cr.save(client);
        return "redirect:/home";
    }

    @GetMapping("formUpdate/{id}")
    public ModelAndView updateForm(@PathVariable long id) {
        ModelAndView mv = new ModelAndView("formUpdate");
        Client client = cr.findById(id);
        mv.addObject("client", client);
        return mv;
    }

    @PostMapping("update/{id}")
    //@RequestMapping(value ="/formUpdate", method = RequestMethod.POST)
    public String update(Client client, @PathVariable long id) {
        cr.save(client);
        return "redirect:/home";
    }


    @RequestMapping("/home")
    public ModelAndView findAll(@RequestParam(value = "nameSearch", required = false) String nameSearch) {
        if (nameSearch == null) {
            ModelAndView mv = new ModelAndView("home");
            Iterable<Client> clients = cr.findAll();
            mv.addObject("clients", clients);
            return mv;
        }
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("clients", cr.findByNameIgnoreCaseContaining(nameSearch));
        return mv;
    }

    @RequestMapping("/deleteClient")
    public String delete(long id){
        Client client = cr.findById(id);
        cr.delete(client);
        return "redirect:/home";
    }
}