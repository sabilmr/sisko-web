package org.project.siskoweb.controller;

import lombok.RequiredArgsConstructor;
import org.project.siskoweb.model.request.FakultasReq;
import org.project.siskoweb.model.response.FakultasRes;
import org.project.siskoweb.service.FakultasService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/fakultas")
public class FakultasController {
    private final FakultasService fakultasService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("pages/fakultas/index");
        modelAndView.addObject("data", fakultasService.get());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView("pages/fakultas/add");
        modelAndView.addObject("data", new FakultasReq());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute FakultasReq fakultasReq){
        this.fakultasService.save(fakultasReq);
        return new ModelAndView("redirect:/fakultas");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("pages/fakultas/edit");

        Optional<FakultasRes> result = this.fakultasService.getById(id);
        if(result.isPresent()){
            modelAndView.addObject("data", result.get());
            return modelAndView;
        }

        return new ModelAndView("redirect:/fakultas");
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute FakultasReq fakultasReq){
        this.fakultasService.update(fakultasReq.getId(), fakultasReq);
        return new ModelAndView("redirect:/fakultas");
    }
}
