package com.nuist.utils;

import com.nuist.bean.ConvertDepartment;
import com.nuist.bean.Department;
import com.nuist.bean.DepartmentResponse;
import com.sun.corba.se.spi.ior.iiop.IIOPFactories;

import java.util.List;

import static com.nuist.test.TestJunit.*;

public class UsertypeConvertUtils {

    //教职工
    public static String getStuffingDepartmentId(List<Department> departments, String departmentId, String Roomname) throws Exception {

        DepartmentResponse departmentResponse = departmentService.selectService(access_token, "");
        //获取企业微信后台教职工的部门id
        String selectId = "";
        for (int i = 0;i < departmentResponse.getDepartment().size();i++){
            if (departmentResponse.getDepartment().get(i).getName().equals("教职工")){
                selectId = departmentResponse.getDepartment().get(i).getId();
                break;
            }
        }

        departmentResponse = departmentService.selectService(access_token, selectId);;
        List<ConvertDepartment> department = departmentResponse.getDepartment();

        String id = "";
        //若Roomname为空则直接返回二级部门id
        if (Roomname.equals("")){
            for (int i = 0;i < departments.size();i++){
                //若该教职工的子部门和父部门id都符合则获得name，然后通过name获取企业微信后台的子部门id
                if ( departmentId.equals( departments.get(i).getDm() ) ) {
                    for (int j = 0;j < department.size();j++){
                        if (departments.get(i).getMc().length() <= 32){
                            if(departments.get(i).getMc().equals(department.get(j).getName())){
                                id = department.get(j).getId();
                                break;
                            }
                        }else {
                            if(departments.get(i).getMc().substring(0,31).equals(department.get(j).getName())){
                                id = department.get(j).getId();
                                break;
                            }
                        }
                    }
                }
            }
        }else {
            for (int i = 0;i < departments.size();i++){
                //若部门和所不匹配则按照所
                if (Roomname.equals(departments.get(i).getDm())) {
                    for (int j = 0;j < departments.size();j++){
                        if (departments.get(j).getDm().equals(departments.get(i).getFjjg())){
                            for (int k = 0;k < department.size();k++){
                                if (departments.get(j).getMc().length() <= 32){
                                    if (departments.get(i).getMc().equals(department.get(k).getName())){
                                        id = department.get(k).getId();
                                        break;
                                    }
                                }else {
                                    if (departments.get(i).getMc().substring(0,31).equals(department.get(k).getName())){
                                        id = department.get(k).getId();
                                        break;
                                    }
                                }

                            }
                        }
                    }

                }
                //若该教职工的子部门和父部门id都符合则获得name，然后通过name获取企业微信后台的子部门id
                if ((Roomname.equals(departments.get(i).getDm())) && (departmentId.equals(departments.get(i).getFjjg()))) {
                    for (int j = 0;j < department.size();j++){
                        if (departments.get(i).getMc().length() <= 32){
                            if(departments.get(i).getMc().equals(department.get(j).getName())){
                                id = department.get(j).getId();
                                break;
                            }
                        }else {
                            if(departments.get(i).getMc().substring(0,31).equals(department.get(j).getName())){
                                id = department.get(j).getId();
                                break;
                            }
                        }

                    }
                }
            }
        }
        return id;
    }
    //学生
    public static String getStuDepartmentId(List<Department> departments, String departmentId, String Roomname) throws Exception {
        DepartmentResponse departmentResponse = departmentService.selectService(access_token, "");
        //获取企业微信后台教职工的部门id
        String selectId = "";
        for (int i = 0;i < departmentResponse.getDepartment().size();i++){
            if (departmentResponse.getDepartment().get(i).getName().equals("学生")){
                selectId = departmentResponse.getDepartment().get(i).getId();
                break;
            }
        }

        departmentResponse = departmentService.selectService(access_token, selectId);;
        List<ConvertDepartment> department = departmentResponse.getDepartment();

        String id = "";
        for (int i = 0;i < departments.size();i++){
            if (Roomname.equals(departments.get(i).getDm())){
                for (int j = 0;j < department.size();j++){
                    if (departments.get(i).getMc().length() <= 32){
                        if (departments.get(i).getMc().equals(department.get(j).getName())){
                            id = department.get(j).getId();
                        }
                    }else {
                        if (departments.get(i).getMc().substring(0,31).equals(department.get(j).getName())){
                            id = department.get(j).getId();
                        }
                    }
                }
            }
        }
        for (int i = 0;i < departments.size();i++){
            //若学院和班级不匹配则按照班级
            if (Roomname.equals(departments.get(i).getDm())) {
                for (int j = 0;j < departments.size();j++){
                    if (departments.get(j).getDm().equals(departments.get(i).getFjjg())){
                        for (int k = 0;k < department.size();k++){
                            if (departments.get(j).getMc().length() <= 32){
                                if (departments.get(i).getMc().equals(department.get(k).getName())){
                                    id = department.get(k).getId();
                                    break;
                                }
                            }else {
                                if (departments.get(i).getMc().substring(0,31).equals(department.get(k).getName())){
                                    id = department.get(k).getId();
                                    break;
                                }
                            }

                        }
                    }
                }

            }
            //若该教职工的子部门和父部门id都符合则获得name，然后通过name获取企业微信后台的子部门id
            if ((Roomname.equals(departments.get(i).getDm())) && (departmentId.equals(departments.get(i).getFjjg()))) {
                for (int j = 0;j < department.size();j++){
                    if (departments.get(i).getMc().length() <= 32){
                        if(departments.get(i).getMc().equals(department.get(j).getName())){
                            id = department.get(j).getId();
                            break;
                        }
                    }else {
                        if(departments.get(i).getMc().substring(0,31).equals(department.get(j).getName())){
                            id = department.get(j).getId();
                            break;
                        }
                    }
                }
            }


        }
        return id;
    }
    //离退休人员
    public static String getRetireDepartmentId(List<Department> departments,  String departmentId, String Roomname) throws Exception {
        DepartmentResponse departmentResponse = departmentService.selectService(access_token, "");
        //获取企业微信后台教职工的部门id
        String selectId = "";
        for (int i = 0;i < departmentResponse.getDepartment().size();i++){
            if (departmentResponse.getDepartment().get(i).getName().equals("离退休人员")){
                selectId = departmentResponse.getDepartment().get(i).getId();
                break;
            }
        }

        departmentResponse = departmentService.selectService(access_token, selectId);;
        List<ConvertDepartment> department = departmentResponse.getDepartment();

        String id = "";

        if (!Roomname.equals("")){
            for (int i = 0;i < departments.size();i++){
                //若该教职工的子部门和父部门id都符合则获得name，然后通过name获取企业微信后台的子部门id
                if ((Roomname.equals(departments.get(i).getDm())) && (departmentId.equals(departments.get(i).getFjjg()))) {
                    for (int j = 0;j < department.size();j++){
                        if (departments.get(i).getMc().length() <= 32){
                            if(departments.get(i).getMc().equals(department.get(j).getName())){
                                id = department.get(j).getId();
                                break;
                            }
                        }else {
                            if(departments.get(i).getMc().substring(0,31).equals(department.get(j).getName())){
                                id = department.get(j).getId();
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (departmentId.equals("")){
            if (Roomname.equals("")){
                id = selectId;
            }
        }else {
            if (Roomname.equals("")){
                for (int i = 0;i < departments.size();i++){
                    //若该教职工的子部门和父部门id都符合则获得name，然后通过name获取企业微信后台的子部门id
                    if ( departmentId.equals( departments.get(i).getDm() ) ) {
                        for (int j = 0;j < department.size();j++){
                            if (departments.get(i).getMc().length() <= 32){
                                if(departments.get(i).getMc().equals(department.get(j).getName())){
                                    id = department.get(j).getId();
                                    break;
                                }
                            }else {
                                if(departments.get(i).getMc().substring(0,31).equals(department.get(j).getName())){
                                    id = department.get(j).getId();
                                    break;
                                }
                            }

                        }
                    }
                }
            }
        }
        return id;

    }
    //校友
    public static String getGraduateDepartmentId(List<Department> departments, String departmentId, String Roomname) throws Exception {
        DepartmentResponse departmentResponse = departmentService.selectService(access_token, "");
        //获取企业微信后台校友的部门id
        String selectId = "";
        for (int i = 0;i < departmentResponse.getDepartment().size();i++){
            if (departmentResponse.getDepartment().get(i).getName().equals("校友")){
                selectId = departmentResponse.getDepartment().get(i).getId();
                break;
            }
        }

        departmentResponse = departmentService.selectService(access_token, selectId);;
        List<ConvertDepartment> department = departmentResponse.getDepartment();

        String id = "";

        if (!Roomname.equals("")){
            for (int i = 0;i < departments.size();i++){
                //若该教职工的子部门和父部门id都符合则获得name，然后通过name获取企业微信后台的子部门id
                if ((Roomname.equals(departments.get(i).getDm())) && (departmentId.equals(departments.get(i).getFjjg()))) {
                    for (int j = 0;j < department.size();j++){
                        if (departments.get(i).getMc().length() <= 32){
                            if(departments.get(i).getMc().equals(department.get(j).getName())){
                                id = department.get(j).getId();
                                break;
                            }
                        }else {
                            if(departments.get(i).getMc().substring(0,31).equals(department.get(j).getName())){
                                id = department.get(j).getId();
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (departmentId.equals("")){
            if (Roomname.equals("")){
                id = selectId;
            }
        }else {
            if (Roomname.equals("")){
                for (int i = 0;i < departments.size();i++){
                    //若该教职工的子部门和父部门id都符合则获得name，然后通过name获取企业微信后台的子部门id
                    if ( departmentId.equals( departments.get(i).getDm() ) ) {
                        for (int j = 0;j < department.size();j++){
                            if (departments.get(i).getMc().length() <= 32){
                                if(departments.get(i).getMc().equals(department.get(j).getName())){
                                    id = department.get(j).getId();
                                    break;
                                }
                            }else {
                                if(departments.get(i).getMc().substring(0,31).equals(department.get(j).getName())){
                                    id = department.get(j).getId();
                                    break;
                                }
                            }

                        }
                    }
                }
            }
        }

        return id;

    }
    //临时工
    public static String getTempDepartmentId(List<Department> departments, String departmentId, String Roomname) throws Exception {
        DepartmentResponse departmentResponse = departmentService.selectService(access_token, "");
        //获取企业微信后台教职工的部门id
        String selectId = "";
        for (int i = 0;i < departmentResponse.getDepartment().size();i++){
            if (departmentResponse.getDepartment().get(i).getName().equals("临时工")){
                selectId = departmentResponse.getDepartment().get(i).getId();
                break;
            }
        }

        departmentResponse = departmentService.selectService(access_token, selectId);;
        List<ConvertDepartment> department = departmentResponse.getDepartment();

        String id = "";

        if (!Roomname.equals("")){
            for (int i = 0;i < departments.size();i++){
                //若该教职工的子部门和父部门id都符合则获得name，然后通过name获取企业微信后台的子部门id
                if ((Roomname.equals(departments.get(i).getDm())) && (departmentId.equals(departments.get(i).getFjjg()))) {
                    for (int j = 0;j < department.size();j++){
                        if (departments.get(i).getMc().length() <= 32){
                            if(departments.get(i).getMc().equals(department.get(j).getName())){
                                id = department.get(j).getId();
                                break;
                            }
                        }else {
                            if(departments.get(i).getMc().substring(0,31).equals(department.get(j).getName())){
                                id = department.get(j).getId();
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (departmentId.equals("")){
            if (Roomname.equals("")){
                id = selectId;
            }
        }else {
            if (Roomname.equals("")){
                for (int i = 0;i < departments.size();i++){
                    //若该教职工的子部门和父部门id都符合则获得name，然后通过name获取企业微信后台的子部门id
                    if ( departmentId.equals( departments.get(i).getDm() ) ) {
                        for (int j = 0;j < department.size();j++){
                            if (departments.get(i).getMc().length() <= 32){
                                if(departments.get(i).getMc().equals(department.get(j).getName())){
                                    id = department.get(j).getId();
                                    break;
                                }
                            }else {
                                if(departments.get(i).getMc().substring(0,31).equals(department.get(j).getName())){
                                    id = department.get(j).getId();
                                    break;
                                }
                            }

                        }
                    }
                }
            }
        }
        return id;
    }

    //部门自聘人员
    public static String getRecruitDepartmentId(List<Department> departments, String departmentId, String Roomname) throws Exception {
        DepartmentResponse departmentResponse = departmentService.selectService(access_token, "");
        //获取企业微信后台教职工的部门id
        String selectId = "";
        for (int i = 0;i < departmentResponse.getDepartment().size();i++){
            if (departmentResponse.getDepartment().get(i).getName().equals("临时工")){
                selectId = departmentResponse.getDepartment().get(i).getId();
                break;
            }
        }

        departmentResponse = departmentService.selectService(access_token, selectId);;
        List<ConvertDepartment> department = departmentResponse.getDepartment();

        String id = "";

        if (!Roomname.equals("")){
            for (int i = 0;i < departments.size();i++){
                //若该教职工的子部门和父部门id都符合则获得name，然后通过name获取企业微信后台的子部门id
                if ((Roomname.equals(departments.get(i).getDm())) && (departmentId.equals(departments.get(i).getFjjg()))) {
                    for (int j = 0;j < department.size();j++){
                        if (departments.get(i).getMc().length() <= 32){
                            if(departments.get(i).getMc().equals(department.get(j).getName())){
                                id = department.get(j).getId();
                                break;
                            }
                        }else {
                            if(departments.get(i).getMc().substring(0,31).equals(department.get(j).getName())){
                                id = department.get(j).getId();
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (departmentId.equals("")){
            if (Roomname.equals("")){
                id = selectId;
            }
        }else {
            if (Roomname.equals("")){
                for (int i = 0;i < departments.size();i++){
                    //若该教职工的子部门和父部门id都符合则获得name，然后通过name获取企业微信后台的子部门id
                    if ( departmentId.equals( departments.get(i).getDm() ) ) {
                        for (int j = 0;j < department.size();j++){
                            if (departments.get(i).getMc().length() <= 32){
                                if(departments.get(i).getMc().equals(department.get(j).getName())){
                                    id = department.get(j).getId();
                                    break;
                                }
                            }else {
                                if(departments.get(i).getMc().substring(0,31).equals(department.get(j).getName())){
                                    id = department.get(j).getId();
                                    break;
                                }
                            }

                        }
                    }
                }
            }
        }
        return id;
    }

    //兼职教职工
    public static String getPartDepartmentId(List<Department> departments, String departmentId, String Roomname) throws Exception {
        DepartmentResponse departmentResponse = departmentService.selectService(access_token, "");
        //获取企业微信后台教职工的部门id
        String selectId = "";
        for (int i = 0;i < departmentResponse.getDepartment().size();i++){
            if (departmentResponse.getDepartment().get(i).getName().equals("临时工")){
                selectId = departmentResponse.getDepartment().get(i).getId();
                break;
            }
        }

        departmentResponse = departmentService.selectService(access_token, selectId);;
        List<ConvertDepartment> department = departmentResponse.getDepartment();

        String id = "";

        if (!Roomname.equals("")){
            for (int i = 0;i < departments.size();i++){
                //若该教职工的子部门和父部门id都符合则获得name，然后通过name获取企业微信后台的子部门id
                if ((Roomname.equals(departments.get(i).getDm())) && (departmentId.equals(departments.get(i).getFjjg()))) {
                    for (int j = 0;j < department.size();j++){
                        if (departments.get(i).getMc().length() <= 32){
                            if(departments.get(i).getMc().equals(department.get(j).getName())){
                                id = department.get(j).getId();
                                break;
                            }
                        }else {
                            if(departments.get(i).getMc().substring(0,31).equals(department.get(j).getName())){
                                id = department.get(j).getId();
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (departmentId.equals("")){
            if (Roomname.equals("")){
                id = selectId;
            }
        }else {
            if (Roomname.equals("")){
                for (int i = 0;i < departments.size();i++){
                    //若该教职工的子部门和父部门id都符合则获得name，然后通过name获取企业微信后台的子部门id
                    if ( departmentId.equals( departments.get(i).getDm() ) ) {
                        for (int j = 0;j < department.size();j++){
                            if (departments.get(i).getMc().length() <= 32){
                                if(departments.get(i).getMc().equals(department.get(j).getName())){
                                    id = department.get(j).getId();
                                    break;
                                }
                            }else {
                                if(departments.get(i).getMc().substring(0,31).equals(department.get(j).getName())){
                                    id = department.get(j).getId();
                                    break;
                                }
                            }

                        }
                    }
                }
            }
        }
        return id;
    }
}
