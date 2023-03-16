package com.fc.demo;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author yfc
 * @date 2022/9/14 10:11
 */
public class Test {

    private static AtomicInteger count = new AtomicInteger(0);

    private static Integer count1 = 0;
    private static String genReqNo1(){
        if(count.get() == 9999) {
            count.set(0);
        }
        return  String.format("%04d", count.incrementAndGet());
    }

    private static String genReqNo2(){
        if(count1 == 9999) {
            count1 = 0;
        }
        return  String.format("%04d", count1++);
    }
    public static void main(String[] args) throws Exception {
//        for (int i = 0; i < 20; i++) {
//            new Thread(()->{
////                System.out.println(genReqNo1());
//                System.out.println(genReqNo2());
//            }).start();
//        }

        System.out.println(String.format("%05d", 123));
    }

    public static String longestCommonPrefix(String[] strs) {

        if (strs.length == 1) return strs[0];
        String first = strs[0];
        for (String s : strs) {
            while (!s.startsWith(first)) {
                if (first.length() == 1) return "";
                first = first.substring(0, first.length() - 1);
            }
        }

        return first;

    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 1) return 1;
        int f = nums[0];
        boolean flag = false;
        for (int num : nums) {
            if(num != f){
                flag = true;
            }
        }
        if(!flag) return 1;

        int k = 0;
        for (int i = 0; i < nums.length - k; i++) {
            if (i + 1 < nums.length - k) {
                int next = nums[i + 1];
                int cur = nums[i];
                if (next == cur) {
                    for (int j = i + 1; j < nums.length; j++) {
                        if(j+1 < nums.length)nums[j] = nums[j + 1];
                    }
                    nums[nums.length - 1] = next;
                    i--;
                    k++;
                }
            }
        }
        return nums.length - k;
    }


    public static boolean isValid(String s) {
        List<Character> list = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if(c == '(' || c == '[' || c == '{' ){
                list.add(c);
            }
            if(c == ')' || c == ']' || c == '}' ){
                if(list.size() == 0) return false;
                if(get(list.get(list.size()-1)) == c){
                    list.remove(list.size()-1);
                }else{
                    return false;
                }
            }
        }
        return list.size() == 0;
    }

    public static Character get(Character c){
        if(c == '(') return ')';
        if(c == '[') return ']';
        if(c == '{') return '}';
        return c;
    }


    public static void ssss() throws Exception {


//        Collection<Object> objects = Collections.synchronizedCollection(Lists.newArrayList());
        File touch = new File("D:\\download\\20221128COMPLAINT_TASK.csv");
        FileInputStream fileInputStream = IoUtil.toStream(touch);
        BufferedReader utf8Reader = IoUtil.getUtf8Reader(fileInputStream);

        String line1 = utf8Reader.readLine();
        String line2 = utf8Reader.readLine();
        System.out.println(line1);
        System.out.println(line2);

        String[] split1 = line1.split("\\|");
        String[] split2 = line2.split("\\|");

        System.out.println(split1.length);
        System.out.println(split2.length);

        for (int i = 0; i < split1.length; i++) {
            System.out.println(split1[i] + " = " + split2[i]);
        }

        System.out.println(Arrays.stream(split1).collect(Collectors.joining("|")));
        System.out.println(Arrays.stream(split2).collect(Collectors.joining("|")));


    }
    public static void aaa(){
        String s =
                "taskid\t外部工单编号\n" +
                "        sub_date\t更新时间\n" +
                "        province_handler\t省公司处理人账户名\n" +
                "        province_accepttime\t省公司接单时间\n" +
                "        province_reason1\t省公司一级原因\n" +
                "        province_reason2\t省公司二级原因\n" +
                "        province_detail\t省公司分析详情\n" +
                "        province_measures1\t省公司一级措施\n" +
                "        province_measures2\t省公司二级措施\n" +
                "        province_plan\t 省公司具体处理方案\n" +
                "        province_subprocess\t省公司涉及子流程\n" +
                "        province_subprocess_content\t省公司子流程内容\n" +
                "        province_subprocess_isclose\t省公司子流程是否修改完毕\n" +
                "        province_nextlink\t省公司下一环节名称\n" +
                "        province_nextlink_submittime\t省公司下一环节提交时间\n" +
                "        province_isup\t省公司是否挂起\n" +
                "        branches_handler\t分公司处理人账户名\n" +
                "        branches_accepttime\t分公司接单时间\n" +
                "        branches_reason1\t分公司一级原因\n" +
                "        branches_reason2\t分公司二级原因\n" +
                "        branches_detail\t分公司分析详情\n" +
                "        branches_measures1\t分公司一级措施\n" +
                "        branches_measures2\t分公司二级措施\n" +
                "        branches_plan\t分公司具体处理方案\n" +
                "        branches_subprocess\t分公司涉及子流程\n" +
                "        branches_subprocess_content\t分公司子流程内容\n" +
                "        branches_subprocess_isclose\t分公司子流程是否修改完毕\n" +
                "        branches_nextlink\t分公司下一环节名称\n" +
                "        branches_nextlink_submittime\t分公司下一环节提交时间\n" +
                "        branches_isup\t分公司是否挂起\n" +
                "        branches_up_time\t分公司申请挂起时间\n" +
                "        branches_up_timelimit\t分公司挂起时长\n" +
                "        branches_appeal\t分公司是否申诉\n" +
                "        tripartite \t是否涉及三方优化\n" +
                "        estimate_link\t提交评估环节\n" +
                "       sys_emestimate_result\t评估结果\n" +
                "        is_reject\t是否驳回重复处理\n" +
                "        is_close\t是否闭环\n" +
                "        closetime\t闭环时间\n" +
                "        is_whitelist\t是否提交白名单\n" +
                "        is_whitelist_adopt\t是否白名单通过\n" +
                "        whitelist_handler\t白名单审批处理人账户名\n" +
                "        icos_taskno\tICOS工单编号\n" +
                "        province_is_solvable\t省公司黑点问题是否可解决\n" +
                "        province_unsolvable_reason\t 省公司不可解决原因\n" +
                "        province_unsolvable_reason_describe\t 省公司不可解决原因详述\n" +
                "        province_solution_plan\t 省公司解决计划\n" +
                "        branches_is_solvable\t分公司黑点问题是否可解决\n" +
                "        branches_unsolvable_reason\t分公司不可解决原因\n" +
                "        branches_unsolvable_reason_describe\t分公司不可解决原因详述\n" +
                "        branches_solution_plan\t 分公司解决计划\n" +
                "        is_assessment\t 是否提交评估\n";

        String aa = "taskid,外部工单编号,sub_date,更新时间,province_handler,省公司处理人账户名,province_accepttime,省公司接单时间,province_reason1,省公司一级原因,province_reason2,省公司二级原因,province_detail,省公司分析详情,province_measures1,省公司一级措施,province_measures2,省公司二级措施,province_plan,省公司具体处理方案,province_subprocess,省公司涉及子流程,province_subprocess_content,省公司子流程内容,province_subprocess_isclose,省公司子流程是否修改完毕,province_nextlink,省公司下一环节名称,province_nextlink_submittime,省公司下一环节提交时间,province_isup,省公司是否挂起,branches_handler,分公司处理人账户名,branches_accepttime,分公司接单时间,branches_reason1,分公司一级原因,branches_reason2,分公司二级原因,branches_detail,分公司分析详情,branches_measures1,分公司一级措施,branches_measures2,分公司二级措施,branches_plan,分公司具体处理方案,branches_subprocess,分公司涉及子流程,branches_subprocess_content,分公司子流程内容,branches_subprocess_isclose,分公司子流程是否修改完毕,branches_nextlink,分公司下一环节名称,branches_nextlink_submittime,分公司下一环节提交时间,branches_isup,分公司是否挂起,branches_up_time,分公司申请挂起时间,branches_up_timelimit,分公司挂起时长,branches_appeal,分公司是否申诉,tripartite,是否涉及三方优化,estimate_link,提交评估环节,sys_emestimate_result,评估结果,is_reject,是否驳回重复处理,is_close,是否闭环,closetime,闭环时间,is_whitelist,是否提交白名单,is_whitelist_adopt,是否白名单通过,whitelist_handler,白名单审批处理人账户名,icos_taskno,ICOS工单编号,province_is_solvable,省公司黑点问题是否可解决,province_unsolvable_reason,省公司不可解决原因,province_unsolvable_reason_describe,省公司不可解决原因详述,province_solution_plan,省公司解决计划,branches_is_solvable,分公司黑点问题是否可解决,branches_unsolvable_reason,分公司不可解决原因,branches_unsolvable_reason_describe,分公司不可解决原因详述,branches_solution_plan,分公司解决计划,is_assessment,是否提交评估";

        JSONArray array = new JSONArray();
        String[] split = aa.split(",");
        for (int i = 0; i < split.length; i+=2) {
            JSONObject object = new JSONObject();
            object.put("field", split[i]);
            object.put("name", split[i+1]);
            array.add(object);
        }

        System.out.println(array.toString());
    }


    public static void sss(){
        String filePath = "D:\\OneDrive\\projectDoc\\cmcc\\安徽融合";
//        String fileName = "ICOS_OUT_NAAS_4G_AH_4FeH9yyKcXklew6iAwpwzbP2c2Qqeecw0i14_20221110.csv";
        String fileName = "ICOS_OUT_NAAS_4G_AH_\\w{36}_{date}.csv";

        String date = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String regex = fileName.replace("{date}",  "20221110");
        File dir = new File(filePath);
        if(dir.exists()){
            for (String s : dir.list()) {
                if(Pattern.matches(regex, s)){
                    System.out.println(s);
                }
            }
        }
    }

    public static void kkk(){

        String str = "[\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":\"HD-JJWTYY\",\n" +
                "        \"dataSourceType\":\"D\",\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"select\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealAnalysis\",\n" +
                "        \"name\":\"mainroot1\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":true,\n" +
                "        \"seq\":330,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"问题一级原因分类\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":null,\n" +
                "        \"dataSourceType\":null,\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"text\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealAnalysis\",\n" +
                "        \"name\":\"mainroot2\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":true,\n" +
                "        \"seq\":340,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"问题二级原因分类\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":null,\n" +
                "        \"dataSourceType\":null,\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"textarea\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealAnalysis\",\n" +
                "        \"name\":\"top10_reason\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":false,\n" +
                "        \"seq\":350,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"TOP10小区问题详情\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":null,\n" +
                "        \"dataSourceType\":null,\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"text\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealSolve\",\n" +
                "        \"name\":\"totalInfoFirstClass\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":true,\n" +
                "        \"seq\":360,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"一级原因\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":null,\n" +
                "        \"dataSourceType\":null,\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"text\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealSolve\",\n" +
                "        \"name\":\"totalInfoSecClass\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":true,\n" +
                "        \"seq\":370,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"二级原因\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":null,\n" +
                "        \"dataSourceType\":null,\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"text\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealSolve\",\n" +
                "        \"name\":\"analyzeDetail\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":true,\n" +
                "        \"seq\":380,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"分析详情\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":\"HD-JJSFJJFA\",\n" +
                "        \"dataSourceType\":\"D\",\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"select\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealSolve\",\n" +
                "        \"name\":\"solveCondition\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":true,\n" +
                "        \"seq\":390,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"是否具备解决方案\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":\"HD-JJBKJJ\",\n" +
                "        \"dataSourceType\":\"D\",\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"select\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealSolve\",\n" +
                "        \"name\":\"cantSolve\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":true,\n" +
                "        \"seq\":400,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"不可解决原因\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":null,\n" +
                "        \"dataSourceType\":null,\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"text\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealSolve\",\n" +
                "        \"name\":\"cantSolveDetail\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":true,\n" +
                "        \"seq\":410,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"不可解决详述\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":\"HD-JJPL\",\n" +
                "        \"dataSourceType\":\"D\",\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"select\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealSolve\",\n" +
                "        \"name\":\"analyzeSolvePlan\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":true,\n" +
                "        \"seq\":420,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"解决计划\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":null,\n" +
                "        \"dataSourceType\":null,\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"text\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealSolve\",\n" +
                "        \"name\":\"firstMeasure\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":true,\n" +
                "        \"seq\":430,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"一级措施\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":null,\n" +
                "        \"dataSourceType\":null,\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"text\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealSolve\",\n" +
                "        \"name\":\"secMeasure\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":true,\n" +
                "        \"seq\":440,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"二级措施\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":null,\n" +
                "        \"dataSourceType\":null,\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"text\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealSolve\",\n" +
                "        \"name\":\"specificPlan\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":true,\n" +
                "        \"seq\":450,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"具体处理方案\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":\"HD-JJZLC\",\n" +
                "        \"dataSourceType\":\"D\",\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"select\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealSolve\",\n" +
                "        \"name\":\"subTask\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":true,\n" +
                "        \"seq\":460,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"涉及子流程\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":null,\n" +
                "        \"dataSourceType\":null,\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"text\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"\",\n" +
                "        \"name\":\"newSite\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":false,\n" +
                "        \"seq\":470,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"新建站enodebid\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":null,\n" +
                "        \"dataSourceType\":null,\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"text\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealSolve\",\n" +
                "        \"name\":\"newSiteCN\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":false,\n" +
                "        \"seq\":480,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"新建站中文名\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":null,\n" +
                "        \"dataSourceType\":null,\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"text\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealSolve\",\n" +
                "        \"name\":\"newSiteLon\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":false,\n" +
                "        \"seq\":490,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"新建站经度\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"businessType\":\"HD\",\n" +
                "        \"cascadeCode\":null,\n" +
                "        \"dataEncryption\":true,\n" +
                "        \"dataSource\":null,\n" +
                "        \"dataSourceType\":null,\n" +
                "        \"defaultValue\":null,\n" +
                "        \"formEdit\":true,\n" +
                "        \"formShow\":true,\n" +
                "        \"formType\":\"text\",\n" +
                "        \"initHide\":false,\n" +
                "        \"jsValid\":null,\n" +
                "        \"modelType\":\"baseDealSolve\",\n" +
                "        \"name\":\"newSiteLat\",\n" +
                "        \"node\":\"jt80edd96e60ae48e3836c1c2e8d6e9a46\",\n" +
                "        \"param1\":null,\n" +
                "        \"param2\":null,\n" +
                "        \"param3\":null,\n" +
                "        \"param4\":null,\n" +
                "        \"param5\":null,\n" +
                "        \"queryTables\":null,\n" +
                "        \"required\":false,\n" +
                "        \"seq\":500,\n" +
                "        \"serverValid\":null,\n" +
                "        \"stage\":\"黑点创建\",\n" +
                "        \"stageCode\":\"HDCJ\",\n" +
                "        \"tableName\":\"t_com_black_info\",\n" +
                "        \"tableSubType\":null,\n" +
                "        \"tipInfo\":null,\n" +
                "        \"title\":\"新建站纬度\"\n" +
                "    }\n" +
                "]";

        JSONArray array = JSONArray.parseArray(str);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            sb.append("@TableField(exist = false)\n" + obj.get("title") +
                    "    private String " + obj.getString("name") + ";\n");
        }
        System.out.println(sb);
    }
}
