package dk.eplusi.dbHelper.controller;

import dk.eplusi.dbHelper.common.DateUtility;
import dk.eplusi.dbHelper.model.eplusi.Youth;
import dk.eplusi.dbHelper.model.eplusi.YouthOrg;
import dk.eplusi.dbHelper.repositorty.*;
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
public class YouthOrgController {

    private Youth setYouthOrgInformation (YouthOrg youthOrg, HttpServletRequest request) throws ParseException {
        String youthId = request.getParameter("youthId");
        if(youthId != null) {
            Optional<Youth> result = youthRepository.findById(Integer.valueOf(youthId));
            if(result.isPresent()) {
                youthOrg.setYouth(result.get());

                youthOrg.setRoleType(roleTypeRepository.getOne(Integer.valueOf(request.getParameter("roleTypeCode"))));
                youthOrg.setOrganization(organizationRepository.getOne(Integer.valueOf(request.getParameter("organizationCode"))));
            }

        }


        youthOrg.setYouthPeer(request.getParameter("youthPeer"));
        youthOrg.setGender(request.getParameter("gender").charAt(0));
        youthOrg.setBirthDate(DateUtility.parse(request.getParameter("birthDate")));
        youthOrg.setCellPhone(request.getParameter("cellPhone"));
        youthOrg.setHomeAddress(request.getParameter("homeAddress"));
        String isBornChrParam = request.getParameter("isBornChr");
        if(isBornChrParam == null)
            youthOrg.setIsBornChr(null);
        else
            youthOrg.setIsBornChr(Integer.valueOf(isBornChrParam));
        String isSelfInParam = request.getParameter("isSelfIn");
        if(isSelfInParam == null)
            youthOrg.setIsSelfIn(null);
        else
            youthOrg.setIsSelfIn(Integer.valueOf(isSelfInParam));
        youthOrg.setGuideName(request.getParameter("guideName"));
        youthOrg.setOccType(occTypeRepository.findById(Integer.valueOf(request.getParameter("occType"))).get());
        youthOrg.setOcc(occRepository.findById(Integer.valueOf(request.getParameter("occ"))).get());
        youthOrg.setBizType(bizTypeRepository.findById(Integer.valueOf(request.getParameter("bizType"))).get());
        youthOrg.setReligionType(religionTypeRepository.findById(Integer.valueOf(request.getParameter("religionType"))).get());
        youthOrg.setChurchRegDate(DateUtility.parse(request.getParameter("churchRegDate")));
        String isAttendingParam = request.getParameter("isAttending");
        if(isAttendingParam == null)
            youthOrg.setIsSelfIn(null);
        else
            youthOrg.setIsSelfIn(Integer.valueOf(isAttendingParam));
        String isRegisteredParam = request.getParameter("isRegistered");
        if(isRegisteredParam == null)
            youthOrg.setIsSelfIn(null);
        else
            youthOrg.setIsSelfIn(Integer.valueOf(isRegisteredParam));
        youthOrg.setUpdateTime(DateUtility.getToday());

        return youthOrg;
    }

    private final YouthRepository youthRepository;
    private final OccTypeRepository occTypeRepository;
    private final OccRepository occRepository;
    private final BizTypeRepository bizTypeRepository;
    private final ReligionTypeRepository religionTypeRepository;
    private final YouthOrgRepository youthOrgRepository;
    private final OrganizationRepository organizationRepository;
    private final RoleTypeRepository roleTypeRepository;

    @Autowired
    public YouthOrgController(YouthRepository youthRepository, OccTypeRepository occTypeRepository,
                              OccRepository occRepository, BizTypeRepository bizTypeRepository,
                              ReligionTypeRepository religionTypeRepository, YouthOrgRepository youthOrgRepository,
                              OrganizationRepository organizationRepository, RoleTypeRepository roleTypeRepository) {
        this.youthRepository = youthRepository;
        this.occTypeRepository = occTypeRepository;
        this.occRepository = occRepository;
        this.bizTypeRepository = bizTypeRepository;
        this.religionTypeRepository = religionTypeRepository;
        this.youthOrgRepository = youthOrgRepository;
        this.organizationRepository = organizationRepository;
        this.roleTypeRepository = roleTypeRepository;
    }

    private static final Map<String, String> keywordMap = new LinkedHashMap<>();
    static {
        keywordMap.put("youthName", "청년 이름");
    }

    @GetMapping(value = "/youthOrgSearch")
    public String youthOrgSearchGet(HttpServletRequest request, Model model, Pageable pageable) throws Exception {
        return youthOrgSearchPost(request, model, pageable);
    }

    @PostMapping(value = "/youthOrgSearch")
    public String youthOrgSearchPost(HttpServletRequest request, Model model, @PageableDefault(size = 20) Pageable pageable) throws Exception {
        Page<YouthOrg> result = null;
        String target = request.getParameter("target");
        String keyword = request.getParameter("keyword");
        model.addAttribute("target", target);
        model.addAttribute("keyword", keyword);
        if (target != null && !target.isEmpty() && keyword != null && !keyword.isEmpty()) {
            switch (target) {
                case "youthName":
                    List<Youth> youthSearched = youthRepository.findByYouthName(keyword);
                    if(!youthSearched.isEmpty())
                        result = youthOrgRepository.findByYouth(youthSearched.get(0), pageable);
                    break;
            }
        }
        if(result == null)
        result = youthOrgRepository.findAll(pageable);

        model.addAttribute("size", result.getTotalElements());
        model.addAttribute("youthOrgList", result.getContent());
        model.addAttribute("keywordMap", keywordMap);

        List<Integer> pageNumbers = new ArrayList<>();
        for(int i = 0; i < result.getTotalPages(); i++)
            pageNumbers.add(i);
        model.addAttribute("pageNumbers", pageNumbers);

        return "youthOrg/youthOrgSearch";
    }

    @PostMapping(value = "youthOrgInsert")
    public String youthOrgInsert(Model model) throws Exception {
        model.addAttribute("occTypeList", occTypeRepository.findAll());
        model.addAttribute("occList", occRepository.findAll());
        model.addAttribute("bizTypeList", bizTypeRepository.findAll());
        model.addAttribute("religionTypeList", religionTypeRepository.findAll());

        return "youthOrg/youthOrgInsert";
    }

    @PostMapping(value = "youthOrgInsertResult")
    public String youthOrgInsertResult(HttpServletRequest request, Model model) throws Exception {
        YouthOrg youthOrg = setYouthOrgInformation(new YouthOrg(), request);
        model.addAttribute("youthOrg", youthOrg);
        YouthOrg saveResult = youthOrgRepository.save(youthOrg);
        model.addAttribute("youthOrgId", saveResult.getYouthOrgId());
        model.addAttribute("success", youthOrg.equals(saveResult));

        return "youthOrg/youthOrgInsertResult";
    }

    @PostMapping(value = "youthOrgModify")
    public String youthOrgModify(HttpServletRequest request, Model model) throws Exception {
        Integer youthOrgId = Integer.valueOf(request.getParameter("youthOrgId"));
        Optional<YouthOrg> result = youthOrgRepository.findById(youthOrgId);
        if(result.isPresent()) {
            YouthOrg youthOrg = result.get();
            model.addAttribute("youth", youthOrg);

//            List<Map<String, Object>> occTypeList = new ArrayList<>();
//            occTypeRepository.findAll().forEach(occType -> {
//                Map<String, Object> occTypeMap = new HashMap<>();
//                occTypeMap.put("key", occType.getOccTypeCode());
//                occTypeMap.put("value", occType.getOccType());
//                if(occType.equals(youthOrg.getOccType()))
//                    occTypeMap.put("selected", true);
//                occTypeList.add(occTypeMap);
//            });
//            model.addAttribute("occTypeList", occTypeList);
//
//            List<Map<String, Object>> occList = new ArrayList<>();
//            occRepository.findAll().forEach(occ -> {
//                Map<String, Object> occMap = new HashMap<>();
//                occMap.put("key", occ.getOccCode());
//                occMap.put("value", occ.getOccName());
//                if(occ.equals(youthOrg.getOcc()))
//                    occMap.put("selected", true);
//                occList.add(occMap);
//            });
//            model.addAttribute("occList", occList);
//
//            List<Map<String, Object>> bizTypeList = new ArrayList<>();
//            bizTypeRepository.findAll().forEach(bizType -> {
//                Map<String, Object> bizTypeMap = new HashMap<>();
//                bizTypeMap.put("key", bizType.getBizTypeCode());
//                bizTypeMap.put("value", bizType.getBizType());
//                if(bizType.equals(youthOrg.getBizType()))
//                    bizTypeMap.put("selected", true);
//                bizTypeList.add(bizTypeMap);
//            });
//            model.addAttribute("bizTypeList", bizTypeList);
//
//            List<Map<String, Object>> religionTypeList = new ArrayList<>();
//            religionTypeRepository.findAll().forEach(religionType -> {
//                Map<String, Object> religionTypeMap = new HashMap<>();
//                religionTypeMap.put("key", religionType.getReligionTypeCode());
//                religionTypeMap.put("value", religionType.getReligionType());
//                if(religionType.equals(youthOrg.getReligionType()))
//                    religionTypeMap.put("selected", true);
//                religionTypeList.add(religionTypeMap);
//            });
//            model.addAttribute("religionTypeList", religionTypeList);
        }

        return "youthOrg/youthOrgModify";
    }

    @PostMapping(value = "youthOrgModifyResult")
    public String youthOrgModifyResult(HttpServletRequest request, Model model) throws Exception {
        Integer youthOrgId = Integer.valueOf(request.getParameter("youthOrgId"));
        Optional<YouthOrg> result = youthOrgRepository.findById(youthOrgId);
        if(result.isPresent()) {
            YouthOrg youthOrg = setYouthOrgInformation(result.get(), request);
            model.addAttribute("youth", youthOrg);
            YouthOrg saveResult = youthOrgRepository.save(youthOrg);
            model.addAttribute("success", youthOrg.equals(saveResult));
        }
        return "youthOrg/youthOrgModifyResult";
    }

    @GetMapping(value = "youthOrgDetail")
    public String youthOrgDetail(HttpServletRequest request, Model model) throws Exception {
        Integer youthOrgId = Integer.valueOf(request.getParameter("youthOrgId"));
        Optional<YouthOrg> result = youthOrgRepository.findById(youthOrgId);
        model.addAttribute("found", result.isPresent());
        if(result.isPresent()) {
            YouthOrg youthOrg = result.get();
            model.addAttribute("youthOrg", youthOrg);
        }
        return "youthOrg/youthOrgDetail";
    }

    @PostMapping(value = "youthOrgDeleteResult")
    public String youthOrgDeleteResult(HttpServletRequest request, Model model) throws Exception {
        Integer youthOrgId = Integer.valueOf(request.getParameter("youthOrgId"));
        Optional<YouthOrg> result = youthOrgRepository.findById(youthOrgId);
        if(result.isPresent()) {
            YouthOrg youthOrg = result.get();
            youthOrgRepository.delete(youthOrg);
            model.addAttribute("name", youthOrg.getYouth().getYouthName());
            model.addAttribute("success", true);
        } else {
            model.addAttribute("success", false);
        }
        return "youthOrg/youthOrgDeleteResult";
    }

    @PostMapping(value = "youthOrgDeleteAllResult")
    public String youthOrgDeleteAllResult(HttpServletRequest request, Model model) throws Exception {
        youthOrgRepository.deleteAll();
        model.addAttribute("success", true);    //TODO 실패조건?
        return "youthOrg/youthOrgDeleteAllResult";
    }
    
}
