package com.tx.app.action.equipment;

import com.tx.app.action.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: chenqingxia
 * Date: 2014/6/25
 * Time: 15:19
 */
@Controller
@RequestMapping("/equipment")
public class DeviceController extends BaseController {
    @RequestMapping(value = "/device", method = RequestMethod.GET)
    public String index(){
        return "/equipment/device/device_index";
    }
}
