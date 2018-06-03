package dk.eplusi.dbHelper.controller;

import dk.eplusi.dbHelper.common.DateUtility;
import dk.eplusi.dbHelper.model.eplusi.ReligionType;
import dk.eplusi.dbHelper.model.eplusi.Youth;
import dk.eplusi.dbHelper.repositorty.*;
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

    private static final Map<String, String> keywordMap = new LinkedHashMap<>();
    static {
        keywordMap.put("youthName", "청년 이름");
        keywordMap.put("youthPeer", "청년 또래");
        keywordMap.put("cellPhone", "핸드폰 번호");
        keywordMap.put("religionType", "신급");
    }

    private Youth setYouthInformation (Youth youth, HttpServletRequest request) throws ParseException {
        youth.setYouthName(request.getParameter("youthName"));
        youth.setYouthPeer(request.getParameter("youthPeer"));
        youth.setGender(request.getParameter("gender").charAt(0));
        youth.setBirthDate(DateUtility.parse(request.getParameter("birthDate")));
        youth.setCellPhone(request.getParameter("cellPhone"));
        youth.setHomeAddress(request.getParameter("homeAddress"));
        String isBornChrParam = request.getParameter("isBornChr");
        if(isBornChrParam == null)
            youth.setIsBornChr(null);
        else
            youth.setIsBornChr(Integer.valueOf(isBornChrParam));
        String isSelfInParam = request.getParameter("isSelfIn");
        if(isSelfInParam == null)
            youth.setIsSelfIn(null);
        else
            youth.setIsSelfIn(Integer.valueOf(isSelfInParam));
        youth.setGuideName(request.getParameter("guideName"));
        youth.setOccType(occTypeRepository.findById(Integer.valueOf(request.getParameter("occType"))).get());
        youth.setOcc(occRepository.findById(Integer.valueOf(request.getParameter("occ"))).get());
        youth.setBizType(bizTypeRepository.findById(Integer.valueOf(request.getParameter("bizType"))).get());
        youth.setReligionType(religionTypeRepository.findById(Integer.valueOf(request.getParameter("religionType"))).get());
        youth.setChurchRegDate(DateUtility.parse(request.getParameter("churchRegDate")));
        String isAttendingParam = request.getParameter("isAttending");
        if(isAttendingParam == null)
            youth.setIsAttending(null);
        else
            youth.setIsAttending(Integer.valueOf(isAttendingParam));
        String isRegisteredParam = request.getParameter("isRegistered");
        if(isRegisteredParam == null)
            youth.setIsRegistered(null);
        else
            youth.setIsRegistered(Integer.valueOf(isRegisteredParam));

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
        model.addAttribute("occTypeList", occTypeRepository.findAll());
        model.addAttribute("occList", occRepository.findAll());
        model.addAttribute("bizTypeList", bizTypeRepository.findAll());
        model.addAttribute("religionTypeList", religionTypeRepository.findAll());

        return "youth/youthInsert";
    }

    @PostMapping(value = "youthInsertResult")
    public String youthInsertResult(HttpServletRequest request, Model model) throws Exception {
        Youth youth = setYouthInformation(new Youth(), request);
        model.addAttribute("youth", youth);
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
            model.addAttribute("youth", youth);

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
            model.addAttribute("youth", youth);
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
            model.addAttribute("youth", youth);
        }
        return "youth/youthDetail";
    }

    @GetMapping(value = "/youthSearch")
    public String youthSearchGet(HttpServletRequest request, Model model, Pageable pageable) throws Exception {
        return youthSearchPost(request, model, pageable);
    }

    @PostMapping(value = "/youthSearch")
    public String youthSearchPost(HttpServletRequest request, Model model, @PageableDefault(size = 20) Pageable pageable) throws Exception {
        Page<Youth> result = null;
        String target = request.getParameter("target");
        String keyword = request.getParameter("keyword");
        model.addAttribute("target", target);
        model.addAttribute("keyword", keyword);
        if (target != null && !target.isEmpty() && keyword != null && !keyword.isEmpty()) {
            switch (target) {
                case "youthName":
                    result = youthRepository.findByYouthName(keyword, pageable);
                    break;
                case "youthPeer":
                    result = youthRepository.findByYouthPeer(keyword, pageable);
                    break;
                case "cellPhone":
                    result = youthRepository.findByCellPhone(keyword, pageable);
                    break;
                case "religionType":
                    List<ReligionType> searchedReligionTypeList = religionTypeRepository.findByReligionType(keyword);
                    if(!searchedReligionTypeList.isEmpty())
                        result = youthRepository.findByReligionType(searchedReligionTypeList.get(0), pageable);
                    break;
            }
        }

        if(result == null)
            result = youthRepository.findAll(pageable);

        model.addAttribute("size", result.getTotalElements());
        model.addAttribute("youths", result.getContent());
        model.addAttribute("keywordMap", keywordMap);

        List<Integer> pageNumbers = new ArrayList<>();
        for(int i = 0; i < result.getTotalPages(); i++)
            pageNumbers.add(i);
        model.addAttribute("pageNumbers", pageNumbers);

        return "youth/youthSearch";
    }

    //TODO 동시 제거 안 됨
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
