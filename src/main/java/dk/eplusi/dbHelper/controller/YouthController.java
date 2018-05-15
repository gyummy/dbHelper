package dk.eplusi.dbHelper.controller;

import dk.eplusi.dbHelper.common.DateUtility;
import dk.eplusi.dbHelper.repositorty.*;
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
import java.text.ParseException;
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

    private String convertIntToMsg(Integer value) {
        if(value == null)
            return "";

        return value == 0 ? "아니오" : "예";
    }

    private Model addYouthDetailToModel(Youth youth, Model model) {
        model.addAttribute("youthId", youth.getYouthId());
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

        return model;
    }

    public Youth setYouthInformation (Youth youth, HttpServletRequest request) throws ParseException {
        youth.setYouthName(request.getParameter("youthName"));
        youth.setYouthPeer(request.getParameter("youthPeer"));
        youth.setGender(request.getParameter("gender").charAt(0));
        youth.setBirthDate(DateUtility.parse(request.getParameter("birthDate")));
        youth.setCellPhone(request.getParameter("cellPhone"));
        youth.setHomeAddress(request.getParameter("homeAddress"));
        youth.setIsBornChr(Integer.valueOf(request.getParameter("isBornChr")));
        youth.setIsSelfIn(Integer.valueOf(request.getParameter("isSelfIn")));
        youth.setGuideName(request.getParameter("guideName"));
        youth.setOccType(occTypeRepository.findById(Integer.valueOf(request.getParameter("occType"))).get());
        youth.setOcc(occRepository.findById(Integer.valueOf(request.getParameter("occ"))).get());
        youth.setBizType(bizTypeRepository.findById(Integer.valueOf(request.getParameter("bizType"))).get());
        youth.setReligionType(religionTypeRepository.findById(Integer.valueOf(request.getParameter("religionType"))).get());
        youth.setChurchRegDate(DateUtility.parse(request.getParameter("churchRegDate")));
        youth.setIsAttending(Integer.valueOf(request.getParameter("isAttending")));
        youth.setIsRegistered(Integer.valueOf(request.getParameter("isRegistered")));
        youth.setUpdateTime(DateUtility.getToday());

        return youth;
    }

    private final YouthRepository youthRepository;
    private final OccTypeRepository occTypeRepository;
    private final OccRepository occRepository;
    private final BizTypeRepository bizTypeRepository;
    private final ReligionTypeRepository religionTypeRepository;

    @Autowired
    public YouthController(YouthRepository youthRepository, OccTypeRepository occTypeRepository, OccRepository occRepository, BizTypeRepository bizTypeRepository, ReligionTypeRepository religionTypeRepository) {
        this.youthRepository = youthRepository;
        this.occTypeRepository = occTypeRepository;
        this.occRepository = occRepository;
        this.bizTypeRepository = bizTypeRepository;
        this.religionTypeRepository = religionTypeRepository;
    }

    @PostMapping(value = "youthInsert")
    public String youthInsert(Model model) throws Exception {
        List<Map<String, Object>> occTypeList = new ArrayList<>();
        occTypeRepository.findAll().forEach(occType -> {
            Map<String, Object> occTypeMap = new HashMap<>();
            occTypeMap.put("key", occType.getOccTypeCode());
            occTypeMap.put("value", occType.getOccType());
            occTypeList.add(occTypeMap);
        });
        model.addAttribute("occTypeList", occTypeList);

        List<Map<String, Object>> occList = new ArrayList<>();
        occRepository.findAll().forEach(occ -> {
            Map<String, Object> occMap = new HashMap<>();
            occMap.put("key", occ.getOccCode());
            occMap.put("value", occ.getOccName());
            occList.add(occMap);
        });
        model.addAttribute("occList", occList);

        List<Map<String, Object>> bizTypeList = new ArrayList<>();
        bizTypeRepository.findAll().forEach(bizType -> {
            Map<String, Object> bizTypeMap = new HashMap<>();
            bizTypeMap.put("key", bizType.getBizTypeCode());
            bizTypeMap.put("value", bizType.getBizType());
            bizTypeList.add(bizTypeMap);
        });
        model.addAttribute("bizTypeList", bizTypeList);

        List<Map<String, Object>> religionTypeList = new ArrayList<>();
        religionTypeRepository.findAll().forEach(religionType -> {
            Map<String, Object> religionTypeMap = new HashMap<>();
            religionTypeMap.put("key", religionType.getReligionTypeCode());
            religionTypeMap.put("value", religionType.getReligionType());
            religionTypeList.add(religionTypeMap);
        });
        model.addAttribute("religionTypeList", religionTypeList);

        return "youth/youthInsert";
    }

    @PostMapping(value = "youthInsertResult")
    public String youthInsertResult(HttpServletRequest request, Model model) throws Exception {
        Youth youth = setYouthInformation(new Youth(), request);
        model = addYouthDetailToModel(youth, model);
        Youth saveResult = youthRepository.save(youth);
        model.addAttribute("youthId", saveResult.getYouthId());
        model.addAttribute("success", youth.equals(saveResult));

        return "youth/youthInsertResult";
    }

    @PostMapping(value = "youthModify")
    public String youthModify(HttpServletRequest request, Model model) throws Exception {
        Integer youthId = Integer.valueOf(request.getParameter("youthId"));
        Optional<Youth> result = youthRepository.findById(youthId);
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
            model.addAttribute("churchRegDate", youth.getChurchRegDate());
            model.addAttribute("isAttending", convertIntToMsg(youth.getIsAttending()));
            model.addAttribute("isRegistered", convertIntToMsg(youth.getIsRegistered()));

            List<Map<String, Object>> occTypeList = new ArrayList<>();
            occTypeRepository.findAll().forEach(occType -> {
                Map<String, Object> occTypeMap = new HashMap<>();
                occTypeMap.put("key", occType.getOccTypeCode());
                occTypeMap.put("value", occType.getOccType());
                if(occType.equals(youth.getOccType()))
                    occTypeMap.put("selected", true);
                occTypeList.add(occTypeMap);
            });
            model.addAttribute("occTypeList", occTypeList);

            List<Map<String, Object>> occList = new ArrayList<>();
            occRepository.findAll().forEach(occ -> {
                Map<String, Object> occMap = new HashMap<>();
                occMap.put("key", occ.getOccCode());
                occMap.put("value", occ.getOccName());
                if(occ.equals(youth.getOcc()))
                    occMap.put("selected", true);
                occList.add(occMap);
            });
            model.addAttribute("occList", occList);

            List<Map<String, Object>> bizTypeList = new ArrayList<>();
            bizTypeRepository.findAll().forEach(bizType -> {
                Map<String, Object> bizTypeMap = new HashMap<>();
                bizTypeMap.put("key", bizType.getBizTypeCode());
                bizTypeMap.put("value", bizType.getBizType());
                if(bizType.equals(youth.getBizType()))
                    bizTypeMap.put("selected", true);
                bizTypeList.add(bizTypeMap);
            });
            model.addAttribute("bizTypeList", bizTypeList);

            List<Map<String, Object>> religionTypeList = new ArrayList<>();
            religionTypeRepository.findAll().forEach(religionType -> {
                Map<String, Object> religionTypeMap = new HashMap<>();
                religionTypeMap.put("key", religionType.getReligionTypeCode());
                religionTypeMap.put("value", religionType.getReligionType());
                if(religionType.equals(youth.getReligionType()))
                    religionTypeMap.put("selected", true);
                religionTypeList.add(religionTypeMap);
            });
            model.addAttribute("religionTypeList", religionTypeList);
        }

        return "youth/youthModify";
    }

    @PostMapping(value = "youthModifyResult")
    public String youthModifyResult(HttpServletRequest request, Model model) throws Exception {
        Integer youthId = Integer.valueOf(request.getParameter("youthId"));
        Optional<Youth> result = youthRepository.findById(youthId);
        if(result.isPresent()) {
            Youth youth = setYouthInformation(result.get(), request);
            model = addYouthDetailToModel(youth, model);
            Youth saveResult = youthRepository.save(youth);
            model.addAttribute("success", youth.equals(saveResult));
        }
        return "youth/youthModifyResult";
    }

    @GetMapping(value = "youthDetail")
    public String youthDetail(HttpServletRequest request, Model model) throws Exception {
        Integer youthId = Integer.valueOf(request.getParameter("youthId"));
        Optional<Youth> result = youthRepository.findById(youthId);
        model.addAttribute("found", result.isPresent());
        if(result.isPresent()) {
            Youth youth = result.get();
            addYouthDetailToModel(youth, model);
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
                    result = youthRepository.findByYouthName(keyword, pageable);
                    break;
                case "youth_peer":
                    result = youthRepository.findByYouthPeer(keyword, pageable);
                    break;
                case "cell_phone":
                    result = youthRepository.findByCellPhone(keyword, pageable);
                    break;
                default:
                    result = youthRepository.findAll(pageable);
                    break;
            }
        } else {
            result = youthRepository.findAll(pageable);
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

    @PostMapping(value = "youthDeleteResult")
    public String youthDeleteResult(HttpServletRequest request, Model model) throws Exception {
        Integer youthId = Integer.valueOf(request.getParameter("youthId"));
        Optional<Youth> result = youthRepository.findById(youthId);
        if(result.isPresent()) {
            Youth youth = result.get();
            youthRepository.delete(youth);
            model.addAttribute("name", youth.getYouthName());
            model.addAttribute("success", true);
        } else {
            model.addAttribute("success", false);
        }
        return "youth/youthDeleteResult";
    }

    @PostMapping(value = "youthDeleteAllResult")
    public String youthDeleteAllResult(HttpServletRequest request, Model model) throws Exception {
        youthRepository.deleteAll();
        model.addAttribute("success", true);    //TODO 실패조건?
        return "youth/youthDeleteAllResult";
    }
}
