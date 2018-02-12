package spring.tutorial.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.tutorial.util.CustomMediaType;

@Controller
public class TestController {


    @RequestMapping(value = "/test",
                    consumes = "test/csv",
                    produces =  MediaType.TEXT_PLAIN_VALUE,
                    method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String handleRequest(@RequestBody TestItemList itemList){
        String s =  itemList.toString();
        return s;
    }

}
