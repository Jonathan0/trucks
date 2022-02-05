package com.food;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TruckErrorHandlerController implements ErrorController{

    @RequestMapping(path = "/error", method = {RequestMethod.GET})
    @ResponseBody
    public String getErrorPath() {
        return "<center><h1>There is no Truck found !</h1></center>";
    }
}
