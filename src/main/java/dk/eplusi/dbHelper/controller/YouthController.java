package dk.eplusi.dbHelper.controller;

import dk.eplusi.dbHelper.dao.OccDao;
import dk.eplusi.dbHelper.dao.OccTypeDao;
import dk.eplusi.dbHelper.dao.YouthDao;
import dk.eplusi.dbHelper.model.code.Occ;
import dk.eplusi.dbHelper.model.eplusi.Youth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Gyummy on 2017-08-10.
 *
 */
@Controller
public class YouthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(YouthController.class);

    private static final Map<String, String> keywordMap = new HashMap<>();
    static {
        keywordMap.put("youth_name", "청년 이름");
        keywordMap.put("youth_peer", "청년 또래");
        keywordMap.put("cell_phone", "핸드폰 번호");
    }

    private static String convertIntToMsg(Integer value) {
        if(value == null)
            return "";

        return value == 0 ? "아니오" : "예";
    }

    private static boolean convertIntToBoolean(Integer value) {
        if(value == null)
            return false;

        return value != 0;
    }

    private final YouthDao youthDao;
    private final OccTypeDao occTypeDao;
    private final OccDao occDao;

    @Autowired
    public YouthController(YouthDao youthDao, OccTypeDao occTypeDao, OccDao occDao) {
        this.youthDao = youthDao;
        this.occTypeDao = occTypeDao;
        this.occDao = occDao;
    }

    //TODO 청년 정보 입력 페이지 생성
    @PostMapping(value = "youthInsert")
    public String youthInsert(HttpServletRequest request, Model model) throws Exception {

        return "youth/youthInsert";
    }

    //TODO 청년 정보 수정 페이지 생성
    @PostMapping(value = "youthModify")
    public String youthModify(HttpServletRequest request, Model model) throws Exception {
        Integer youthId = Integer.valueOf(request.getParameter("youthId"));
        Optional<Youth> result = youthDao.findById(youthId);
        if(result.isPresent()) {
            Youth youth = result.get();
            model.addAttribute("youthId", youthId);
            model.addAttribute("youthName", youth.getYouthName());
            model.addAttribute("youthPeer", youth.getYouthPeer());
            if(youth.getGender() != null)
                model.addAttribute("isMale", youth.getGender().equals('M'));
            model.addAttribute("birthDate", youth.getBirthDate());
            model.addAttribute("cellPhone", youth.getCellPhone());
            model.addAttribute("homeAddress", youth.getHomeAddress());
            model.addAttribute("isBornChr", convertIntToMsg(youth.getIsBornChr()));
            model.addAttribute("isSelfIn", convertIntToMsg(youth.getIsSelfIn()));
            model.addAttribute("guideName", youth.getGuideName());
            if(youth.getOccType() != null)
                model.addAttribute("occType", youth.getOccType().getOccType());
            if(youth.getOcc() != null)
                model.addAttribute("occ", youth.getOcc().getOccName());
            if(youth.getBizType() != null)
                model.addAttribute("bizType", youth.getBizType().getBizType());
            if(youth.getReligionType() != null)
                model.addAttribute("religionType", youth.getReligionType().getReligionType());
            model.addAttribute("churchRegDate", youth.getChurchRegDate());
            model.addAttribute("isAttending", convertIntToMsg(youth.getIsAttending()));
            model.addAttribute("isRegistered", convertIntToMsg(youth.getIsRegistered()));

            List<Map<String, Object>> occTypeList = new ArrayList<>();
            occTypeDao.findAll().forEach(occType -> {
                Map<String, Object> occTypeMap = new HashMap<>();
                occTypeMap.put("key", occType.getOccTypeCode());
                occTypeMap.put("value", occType.getOccType());
                occTypeList.add(occTypeMap);
            });
            model.addAttribute("occTypeList", occTypeList);

            List<Map<String, Object>> occList = new ArrayList<>();
            occDao.findAll().forEach(occ -> {
                Map<String, Object> occMap = new HashMap<>();
                occMap.put("key", occType.getOccCode());
                occMap.put("value", occType.getOcc());
                occList.add(occMap);
            });
            model.addAttribute("occTypeList", occList);
        }

        return "youth/youthModify";
    }

    @PostMapping(value = "youthModifyResult")
    public String youthModifyResult(HttpServletRequest request, Model model) throws Exception {

        return "youth/youthModify";
    }

    @GetMapping(value = "youthDetail")
    public String youthDetail(HttpServletRequest request, Model model) throws Exception {
        Integer youthId = Integer.valueOf(request.getParameter("youthId"));

        Optional<Youth> result = youthDao.findById(youthId);
        model.addAttribute("found", result.isPresent());
        if(result.isPresent()) {
            Youth youth = result.get();
            model.addAttribute("youthId", youthId);
            model.addAttribute("youthName", youth.getYouthName());
            model.addAttribute("youthPeer", youth.getYouthPeer());
            if(youth.getGender() != null) {
                if (youth.getGender().equals('M'))
                    model.addAttribute("gender", "남");
                else
                    model.addAttribute("gender", "여");
            }
            model.addAttribute("birthDate", youth.getBirthDate());
            model.addAttribute("cellPhone", youth.getCellPhone());
            model.addAttribute("homeAddress", youth.getHomeAddress());
            model.addAttribute("isBornChr", convertIntToMsg(youth.getIsBornChr()));
            model.addAttribute("isSelfIn", convertIntToMsg(youth.getIsSelfIn()));
            model.addAttribute("guideName", youth.getGuideName());
            if(youth.getOccType() != null)
                model.addAttribute("occType", youth.getOccType().getOccType());
            if(youth.getOcc() != null)
                model.addAttribute("occ", youth.getOcc().getOccName());
            if(youth.getBizType() != null)
                model.addAttribute("bizType", youth.getBizType().getBizType());
            if(youth.getReligionType() != null)
                model.addAttribute("religionType", youth.getReligionType().getReligionType());
            model.addAttribute("churchRegDate", youth.getChurchRegDate());
            model.addAttribute("isAttending", convertIntToMsg(youth.getIsAttending()));
            model.addAttribute("isRegistered", convertIntToMsg(youth.getIsRegistered()));
        }

        return "youth/youthDetail";
    }

    @GetMapping(value = "/youthSearch")
    public String youthSearchGet(HttpServletRequest request, Model model, Pageable pageable) throws Exception {
        return youthSearchPost(request, model, pageable);
    }

    @PostMapping(value = "/youthSearch")
    public String youthSearchPost(HttpServletRequest request, Model model, @PageableDefault(size = 20) Pageable pageable) throws Exception {
        Page<Youth> result;
        String target = request.getParameter("target");
        String keyword = request.getParameter("keyword");
        if(target != null && !target.isEmpty() && keyword != null && !keyword.isEmpty()) {
            switch (target) {
                case "youth_name":
                    result = youthDao.findByYouthName(keyword, pageable);
                    break;
                case "youth_peer":
                    result = youthDao.findByYouthPeer(keyword, pageable);
                    break;
                case "cell_phone":
                    result = youthDao.findByCellPhone(keyword, pageable);
                    break;
                default:
                    result = youthDao.findAll(pageable);
                    break;
            }
        } else {
            result = youthDao.findAll(pageable);
        }
        model.addAttribute("found", result.getTotalElements() > 0);
        model.addAttribute("size", result.getTotalElements());
        model.addAttribute("youths", result.getContent());

        List<Map<String, String>> keywordList = new ArrayList<>();
        keywordMap.forEach((key, name) -> {
            Map<String, String> keywordMap = new HashMap<>();
            keywordMap.put("key", key);
            keywordMap.put("name", name);
            keywordList.add(keywordMap);
        });
        model.addAttribute("keywordList", keywordList);

        List<Map<String, Integer>> pageNumbers = new ArrayList<>();
        for(int i = 0; i < result.getTotalPages(); i++) {
            Map<String, Integer> pageNumber = new HashMap<>();
            pageNumber.put("value", i);
            pageNumber.put("display", i + 1);
            pageNumbers.add(pageNumber);
        }
        model.addAttribute("pageNumbers", pageNumbers);

        return "youth/youthSearch";
    }

}
