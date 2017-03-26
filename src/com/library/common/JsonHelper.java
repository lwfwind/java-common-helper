package com.library.common;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The type Json helper.
 */
public class JsonHelper {

    /**
     * 简单判断是否是json格式的字符串
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isJson(String str) {
        return str.startsWith("{") && str.contains(":") && str.endsWith("}") || str.startsWith("[") && str.endsWith("]");
    }

    private static void recursionGetPairs(Map<String, String> pairMap, ObjectMapper mapper, String json, String parentKey) {
        try {
            JsonNode rootNode = mapper.readTree(json);
            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
            while (fieldsIterator.hasNext()) {
                Map.Entry<String, JsonNode> field = fieldsIterator.next();
                String value = StringHelper.unquote(String.valueOf(field.getValue()));
                pairMap.put(field.getKey(), value);
                if (!parentKey.equals("")) {
                    pairMap.put(parentKey + "." + field.getKey(), value);
                }
                if (isJson(value)) {
                    if (parentKey.equals("")) {
                        recursionGetPairs(pairMap, mapper, value, field.getKey());
                    } else {
                        recursionGetPairs(pairMap, mapper, value, parentKey + "." + field.getKey());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse json to pairs map.
     *
     * @param json the json
     * @return the map
     */
    public static Map<String, String> parseJsonToPairs(String json) {
        Map<String, String> pairMap = new HashMap<String, String>();
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        if (isJson(json)) {
            recursionGetPairs(pairMap, mapper, json, "");
        }
        return pairMap;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String jsonStr = "{\n" +
                " \"api\": \"mtop.film.mtoporderapi.getticketdetail\",\n" +
                " \"data\": {\n" +
                "  \"returnCode\": \"0\",\n" +
                "  \"returnValue\": {\n" +
                "   \"activities\": [\n" +
                "    {\n" +
                "     \"activityExtType\": \"0\",\n" +
                "     \"activitySeatCount\": \"1\",\n" +
                "     \"activityTitle\": \"爆米花\",\n" +
                "     \"joinCount\": \"0\",\n" +
                "     \"joinLimit\": \"1\",\n" +
                "     \"saleInfo\": \"爆米花\",\n" +
                "     \"seatLimit\": \"2\"\n" +
                "    }\n" +
                "   ],\n" +
                "   \"aliPayUrl\": \"http://maliprod.stable.alipay.net/w/trade_pay.do?refer=tbc&alipay_trade_no=2017031021001001550200320620&s_id=16783887b5de51cccad970eb0693509c\",\n" +
                "   \"alipayOrderId\": \"2017031021001001550200320620\",\n" +
                "   \"alipayServiceUrl\": \"https://h5.m.taobao.com/alicare/index.html?from=tpp_detail&bu=tpp\",\n" +
                "   \"alwaysGO\": \"true\",\n" +
                "   \"amount\": \"1700\",\n" +
                "   \"bizType\": \"SEAT\",\n" +
                "   \"buyerPhone\": \"13913913999\",\n" +
                "   \"cinemaAdr\": \"黄浦区打浦桥\",\n" +
                "   \"cinemaId\": \"11095105\",\n" +
                "   \"cinemaName\": \"大地新电商测试影院\",\n" +
                "   \"cinemaPhone\": \"021-888888888\",\n" +
                "   \"cinemaPhones\": [\n" +
                "    {\n" +
                "     \"areaCode\": \"021\",\n" +
                "     \"phoneNumber\": \"888888888\",\n" +
                "     \"phoneString\": \"021-888888888\"\n" +
                "    }\n" +
                "   ],\n" +
                "   \"codes\": [\n" +
                "    {\n" +
                "     \"code\": \"14014307\",\n" +
                "     \"name\": \"取票号\"\n" +
                "    }\n" +
                "   ],\n" +
                "   \"displayOrder\": [\n" +
                "    \"1\"\n" +
                "   ],\n" +
                "   \"easterEggsCount\": \"2\",\n" +
                "   \"easterEggsInfo\": \"两个彩蛋，错过不要\",\n" +
                "   \"fullTicketStatus\": \"USED\",\n" +
                "   \"gmtCreate\": \"1489138968\",\n" +
                "   \"hallName\": \"2号厅\",\n" +
                "   \"instruction\": \"1、请提前到达影院现场，找到自助取票机，打印纸质电影票，完成取票。\\n2、如现场自助取票机无法打印电影票，请联系影院工作人员处理。\\n3、凭打印好的纸质电影票，检票入场观影。\\n\",\n" +
                "   \"machineName\": \"\",\n" +
                "   \"noSmsTip\": \"是否购票成功以淘票票为准，短信仅供参考\",\n" +
                "   \"nowTime\": \"1489386505\",\n" +
                "   \"overallStatus\": \"USED\",\n" +
                "   \"partnerCode\": \"dadinew\",\n" +
                "   \"payDate\": \"1489139000\",\n" +
                "   \"payEndTime\": \"1489139861\",\n" +
                "   \"poster\": \"i4/TB1tNb3XXXXXXasXXXXXXXXXXXX_.jpg\",\n" +
                "   \"providerName\": \"大地影院\",\n" +
                "   \"refundDetail\": {\n" +
                "    \"cinemaCanQueryPrintingStatus\": \"true\",\n" +
                "    \"refundCount\": \"3\",\n" +
                "    \"refundExceptionCode\": \"0\",\n" +
                "    \"refundReasons\": {\n" +
                "     \"1\": \"买错了（如买错影院、场次、座位）\",\n" +
                "     \"8\": \"计划有变/没时间看/去不了\",\n" +
                "     \"64\": \"影院原因（放映机坏了、场次取消了）\",\n" +
                "     \"512\": \"买贵了\",\n" +
                "     \"1073741824\": \"其他\"\n" +
                "    },\n" +
                "    \"refundUserRightAmount\": \"1360\",\n" +
                "    \"refundUserRightAmountComfirmAlert\": \"退票无忧将退实付票价的80%，退票时请核对金额\",\n" +
                "    \"refundUserRightAmountPercentTips\": \"开场前可退实付票价的80%\",\n" +
                "    \"refundUserRightCount\": \"0\",\n" +
                "    \"refundUserRightDesc\": \"未取票开场前可退实付票价的80%（我的会员特权）\",\n" +
                "    \"refundUserRightSalesGoodDesc\": \"(￥17.0X80%)退实付票价的80%\",\n" +
                "    \"refundUserRightUrl\": \"http://h5.waptest.taobao.com/app/moviemember/pages/member/rights-detail-returnticket.html?level=V3\",\n" +
                "    \"refundable\": \"false\",\n" +
                "    \"saleRefundable\": \"false\",\n" +
                "    \"supplierRefundDesc\": \"1、仅标有【退票】标识的影院支持退票\\n2、未取票时开场前支持退票（时间以各家影院为准），万达有部分场次不支持退票，在确认订单页支付时会有说明已选购场次是否支持退换票\\n3、每个用户每月（自然月）最多能申请3次退票，同一账号、手机号、移动设备视为同一用户\\n4、仅支持整订单退票，暂不支持分座位退票\\n5、 如果购买了小食，暂不支持退换，放映结束前可继续使用\\n6、如果购票参加了特惠活动，退票后，不可再参加该活动\\n7、如果购票使用了兑换券，暂不支持退票；如果购票使用了代金券，退票时不退还代金券\\n8、退款将在1-10个工作日内原路退回到你的支付账户\",\n" +
                "    \"syncRefund\": \"false\"\n" +
                "   },\n" +
                "   \"refundFee\": \"1700\",\n" +
                "   \"refundable\": \"false\",\n" +
                "   \"saleGoods\": \"爆米花\",\n" +
                "   \"scheduleRefundable\": \"false\",\n" +
                "   \"seatInfo\": [\n" +
                "    \"4排7座\"\n" +
                "   ],\n" +
                "   \"servicePhone\": \"0571-88157838\",\n" +
                "   \"showEndTime\": \"1489148580\",\n" +
                "   \"showH5Url\": \"http://wapp.waptest.taobao.com/app/movie/pages/index/show-detail.html?showid=129346948&hasbuybtn=false\",\n" +
                "   \"showId\": \"129346948\",\n" +
                "   \"showTime\": \"1489141200\",\n" +
                "   \"tbOrderId\": \"210922809409525\",\n" +
                "   \"ticketNumber\": \"1\",\n" +
                "   \"title\": \"魔兽\",\n" +
                "   \"version\": \"国语 英语 3D\"\n" +
                "  }\n" +
                " },\n" +
                " \"ret\": [\n" +
                "  \"SUCCESS::调用成功\"\n" +
                " ],\n" +
                " \"v\": \"5.0\"\n" +
                "}";
        parseJsonToPairs(jsonStr);

        jsonStr="{\n" +
                "\t\"api\": \"mtop.film.mtoporderapi.getbiztickets\",\n" +
                "\t\"data\": {\n" +
                "\t\t\"returnCode\": \"0\",\n" +
                "\t\t\"returnValue\": {\n" +
                "\t\t\t\"isEnd\": \"true\",\n" +
                "\t\t\t\"mark\": \"SEAT:20:true,COUPON:0:false,FCODE:0:false,1\",\n" +
                "\t\t\t\"nowTime\": \"1490020592\",\n" +
                "\t\t\t\"tickets\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"bizType\": \"SEAT\",\n" +
                "\t\t\t\t\t\"cinemaId\": \"11095105\",\n" +
                "\t\t\t\t\t\"cinemaName\": \"大地新电商测试影院\",\n" +
                "\t\t\t\t\t\"codes\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"code\": \"14014307\",\n" +
                "\t\t\t\t\t\t\t\"name\": \"取票号\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"count\": \"2\",\n" +
                "\t\t\t\t\t\"createTime\": \"1490015781\",\n" +
                "\t\t\t\t\t\"displayOrder\": [\n" +
                "\t\t\t\t\t\t\"1\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"fcodeBuyer\": \"true\",\n" +
                "\t\t\t\t\t\"fullTicketStatus\": \"TRADE_SUCCESS\",\n" +
                "\t\t\t\t\t\"hallName\": \"3号厅\",\n" +
                "\t\t\t\t\t\"hasOnlineSales\": \"false\",\n" +
                "\t\t\t\t\t\"modifiedTime\": \"1490015816\",\n" +
                "\t\t\t\t\t\"payEndTime\": \"1490015799\",\n" +
                "\t\t\t\t\t\"poster\": \"i1/TB1f5IXXXXXXXbJXFXXXXXXXXXX_.jpg\",\n" +
                "\t\t\t\t\t\"seatList\": [\n" +
                "\t\t\t\t\t\t\"7排11座\",\n" +
                "\t\t\t\t\t\t\"7排10座\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"showEndTime\": \"1490152380\",\n" +
                "\t\t\t\t\t\"showId\": \"129346948\",\n" +
                "\t\t\t\t\t\"showName\": \"魔兽\",\n" +
                "\t\t\t\t\t\"showTime\": \"1490145000\",\n" +
                "\t\t\t\t\t\"status\": \"CANUSE\",\n" +
                "\t\t\t\t\t\"tbOrderId\": \"1490015781170999\",\n" +
                "\t\t\t\t\t\"ticketContent\": \"{\\\"codes\\\":{\\\"取票号\\\":\\\"14014307\\\"},\\\"info\\\":\\\"请您使用【取票号】到影院【大地数字影院】自助终端机或柜台取票，并对号入座。如有问题致电淘宝客服：0571-88157838\\\"}\",\n" +
                "\t\t\t\t\t\"version\": \"国语 2D\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"bizType\": \"SEAT\",\n" +
                "\t\t\t\t\t\"cinemaId\": \"11095105\",\n" +
                "\t\t\t\t\t\"cinemaName\": \"大地新电商测试影院\",\n" +
                "\t\t\t\t\t\"codes\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"code\": \"14014307\",\n" +
                "\t\t\t\t\t\t\t\"name\": \"取票号\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"count\": \"2\",\n" +
                "\t\t\t\t\t\"createTime\": \"1490014811\",\n" +
                "\t\t\t\t\t\"displayOrder\": [\n" +
                "\t\t\t\t\t\t\"1\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"fcodeBuyer\": \"true\",\n" +
                "\t\t\t\t\t\"fullTicketStatus\": \"TRADE_SUCCESS\",\n" +
                "\t\t\t\t\t\"hallName\": \"3号厅\",\n" +
                "\t\t\t\t\t\"hasOnlineSales\": \"false\",\n" +
                "\t\t\t\t\t\"modifiedTime\": \"1490014821\",\n" +
                "\t\t\t\t\t\"payEndTime\": \"1490014820\",\n" +
                "\t\t\t\t\t\"poster\": \"i1/TB1f5IXXXXXXXbJXFXXXXXXXXXX_.jpg\",\n" +
                "\t\t\t\t\t\"seatList\": [\n" +
                "\t\t\t\t\t\t\"3排8座\",\n" +
                "\t\t\t\t\t\t\"3排7座\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"showEndTime\": \"1490152380\",\n" +
                "\t\t\t\t\t\"showId\": \"129346948\",\n" +
                "\t\t\t\t\t\"showName\": \"魔兽\",\n" +
                "\t\t\t\t\t\"showTime\": \"1490145000\",\n" +
                "\t\t\t\t\t\"status\": \"CANUSE\",\n" +
                "\t\t\t\t\t\"tbOrderId\": \"1490014811189999\",\n" +
                "\t\t\t\t\t\"ticketContent\": \"{\\\"codes\\\":{\\\"取票号\\\":\\\"14014307\\\"},\\\"info\\\":\\\"请您使用【取票号】到影院【大地数字影院】自助终端机或柜台取票，并对号入座。如有问题致电淘宝客服：0571-88157838\\\"}\",\n" +
                "\t\t\t\t\t\"version\": \"国语 2D\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"bizType\": \"SEAT\",\n" +
                "\t\t\t\t\t\"cinemaId\": \"11095105\",\n" +
                "\t\t\t\t\t\"cinemaName\": \"大地新电商测试影院\",\n" +
                "\t\t\t\t\t\"codes\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"code\": \"14014307\",\n" +
                "\t\t\t\t\t\t\t\"name\": \"取票号\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"count\": \"2\",\n" +
                "\t\t\t\t\t\"createTime\": \"1490013434\",\n" +
                "\t\t\t\t\t\"displayOrder\": [\n" +
                "\t\t\t\t\t\t\"1\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"fcodeBuyer\": \"true\",\n" +
                "\t\t\t\t\t\"fullTicketStatus\": \"REFUNDING\",\n" +
                "\t\t\t\t\t\"hallName\": \"2号厅\",\n" +
                "\t\t\t\t\t\"hasOnlineSales\": \"false\",\n" +
                "\t\t\t\t\t\"modifiedTime\": \"1490014821\",\n" +
                "\t\t\t\t\t\"payEndTime\": \"1490013450\",\n" +
                "\t\t\t\t\t\"poster\": \"i1/TB1f5IXXXXXXXbJXFXXXXXXXXXX_.jpg\",\n" +
                "\t\t\t\t\t\"seatList\": [\n" +
                "\t\t\t\t\t\t\"2排6座\",\n" +
                "\t\t\t\t\t\t\"2排7座\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"showEndTime\": \"1490149680\",\n" +
                "\t\t\t\t\t\"showId\": \"129346948\",\n" +
                "\t\t\t\t\t\"showName\": \"魔兽\",\n" +
                "\t\t\t\t\t\"showTime\": \"1490142300\",\n" +
                "\t\t\t\t\t\"status\": \"CANUSE\",\n" +
                "\t\t\t\t\t\"tbOrderId\": \"1490013434360999\",\n" +
                "\t\t\t\t\t\"ticketContent\": \"{\\\"codes\\\":{\\\"取票号\\\":\\\"14014307\\\"},\\\"info\\\":\\\"请您使用【取票号】到影院【大地数字影院】自助终端机或柜台取票，并对号入座。如有问题致电淘宝客服：0571-88157838\\\"}\",\n" +
                "\t\t\t\t\t\"version\": \"国语 英语 3D\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"bizType\": \"SEAT\",\n" +
                "\t\t\t\t\t\"cinemaId\": \"11095105\",\n" +
                "\t\t\t\t\t\"cinemaName\": \"大地新电商测试影院\",\n" +
                "\t\t\t\t\t\"codes\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"code\": \"14014307\",\n" +
                "\t\t\t\t\t\t\t\"name\": \"取票号\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"count\": \"2\",\n" +
                "\t\t\t\t\t\"createTime\": \"1490013346\",\n" +
                "\t\t\t\t\t\"displayOrder\": [\n" +
                "\t\t\t\t\t\t\"1\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"fcodeBuyer\": \"true\",\n" +
                "\t\t\t\t\t\"fullTicketStatus\": \"TRADE_SUCCESS\",\n" +
                "\t\t\t\t\t\"hallName\": \"5号厅\",\n" +
                "\t\t\t\t\t\"hasOnlineSales\": \"false\",\n" +
                "\t\t\t\t\t\"modifiedTime\": \"1490013351\",\n" +
                "\t\t\t\t\t\"payEndTime\": \"1490013350\",\n" +
                "\t\t\t\t\t\"poster\": \"i1/TB1f5IXXXXXXXbJXFXXXXXXXXXX_.jpg\",\n" +
                "\t\t\t\t\t\"seatList\": [\n" +
                "\t\t\t\t\t\t\"2排8座\",\n" +
                "\t\t\t\t\t\t\"2排9座\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"showEndTime\": \"1490056380\",\n" +
                "\t\t\t\t\t\"showId\": \"129346948\",\n" +
                "\t\t\t\t\t\"showName\": \"魔兽\",\n" +
                "\t\t\t\t\t\"showTime\": \"1490049000\",\n" +
                "\t\t\t\t\t\"status\": \"CANUSE\",\n" +
                "\t\t\t\t\t\"tbOrderId\": \"1490013346350999\",\n" +
                "\t\t\t\t\t\"ticketContent\": \"{\\\"codes\\\":{\\\"取票号\\\":\\\"14014307\\\"},\\\"info\\\":\\\"请您使用【取票号】到影院【大地数字影院】自助终端机或柜台取票，并对号入座。如有问题致电淘宝客服：0571-88157838\\\"}\",\n" +
                "\t\t\t\t\t\"version\": \"国语 英语 3D\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"bizType\": \"SEAT\",\n" +
                "\t\t\t\t\t\"cinemaId\": \"11095105\",\n" +
                "\t\t\t\t\t\"cinemaName\": \"大地新电商测试影院\",\n" +
                "\t\t\t\t\t\"codes\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"code\": \"14014307\",\n" +
                "\t\t\t\t\t\t\t\"name\": \"取票号\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"count\": \"2\",\n" +
                "\t\t\t\t\t\"createTime\": \"1490013215\",\n" +
                "\t\t\t\t\t\"displayOrder\": [\n" +
                "\t\t\t\t\t\t\"1\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"fcodeBuyer\": \"true\",\n" +
                "\t\t\t\t\t\"fullTicketStatus\": \"REFUNDING\",\n" +
                "\t\t\t\t\t\"hallName\": \"5号厅\",\n" +
                "\t\t\t\t\t\"hasOnlineSales\": \"false\",\n" +
                "\t\t\t\t\t\"modifiedTime\": \"1490013351\",\n" +
                "\t\t\t\t\t\"payEndTime\": \"1490013240\",\n" +
                "\t\t\t\t\t\"poster\": \"i1/TB1f5IXXXXXXXbJXFXXXXXXXXXX_.jpg\",\n" +
                "\t\t\t\t\t\"seatList\": [\n" +
                "\t\t\t\t\t\t\"3排9座\",\n" +
                "\t\t\t\t\t\t\"3排10座\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"showEndTime\": \"1490056380\",\n" +
                "\t\t\t\t\t\"showId\": \"129346948\",\n" +
                "\t\t\t\t\t\"showName\": \"魔兽\",\n" +
                "\t\t\t\t\t\"showTime\": \"1490049000\",\n" +
                "\t\t\t\t\t\"status\": \"CANUSE\",\n" +
                "\t\t\t\t\t\"tbOrderId\": \"1490013215917999\",\n" +
                "\t\t\t\t\t\"ticketContent\": \"{\\\"codes\\\":{\\\"取票号\\\":\\\"14014307\\\"},\\\"info\\\":\\\"请您使用【取票号】到影院【大地数字影院】自助终端机或柜台取票，并对号入座。如有问题致电淘宝客服：0571-88157838\\\"}\",\n" +
                "\t\t\t\t\t\"version\": \"国语 英语 3D\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"bizType\": \"SEAT\",\n" +
                "\t\t\t\t\t\"cinemaId\": \"11095105\",\n" +
                "\t\t\t\t\t\"cinemaName\": \"大地新电商测试影院\",\n" +
                "\t\t\t\t\t\"codes\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"code\": \"14014307\",\n" +
                "\t\t\t\t\t\t\t\"name\": \"取票号\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"count\": \"2\",\n" +
                "\t\t\t\t\t\"createTime\": \"1490012942\",\n" +
                "\t\t\t\t\t\"displayOrder\": [\n" +
                "\t\t\t\t\t\t\"1\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"fcodeBuyer\": \"true\",\n" +
                "\t\t\t\t\t\"fullTicketStatus\": \"TRADE_SUCCESS\",\n" +
                "\t\t\t\t\t\"hallName\": \"5号厅\",\n" +
                "\t\t\t\t\t\"hasOnlineSales\": \"false\",\n" +
                "\t\t\t\t\t\"modifiedTime\": \"1490012951\",\n" +
                "\t\t\t\t\t\"payEndTime\": \"1490012950\",\n" +
                "\t\t\t\t\t\"poster\": \"i1/TB1f5IXXXXXXXbJXFXXXXXXXXXX_.jpg\",\n" +
                "\t\t\t\t\t\"seatList\": [\n" +
                "\t\t\t\t\t\t\"6排7座\",\n" +
                "\t\t\t\t\t\t\"6排8座\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"showEndTime\": \"1490023980\",\n" +
                "\t\t\t\t\t\"showId\": \"129346948\",\n" +
                "\t\t\t\t\t\"showName\": \"魔兽\",\n" +
                "\t\t\t\t\t\"showTime\": \"1490016600\",\n" +
                "\t\t\t\t\t\"status\": \"CANUSE\",\n" +
                "\t\t\t\t\t\"tbOrderId\": \"1490012942682999\",\n" +
                "\t\t\t\t\t\"ticketContent\": \"{\\\"codes\\\":{\\\"取票号\\\":\\\"14014307\\\"},\\\"info\\\":\\\"请您使用【取票号】到影院【大地数字影院】自助终端机或柜台取票，并对号入座。如有问题致电淘宝客服：0571-88157838\\\"}\",\n" +
                "\t\t\t\t\t\"version\": \"国语 英语 3D\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"bizType\": \"SEAT\",\n" +
                "\t\t\t\t\t\"cinemaId\": \"11095105\",\n" +
                "\t\t\t\t\t\"cinemaName\": \"大地新电商测试影院\",\n" +
                "\t\t\t\t\t\"codes\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"code\": \"14014307\",\n" +
                "\t\t\t\t\t\t\t\"name\": \"取票号\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"count\": \"2\",\n" +
                "\t\t\t\t\t\"createTime\": \"1490011951\",\n" +
                "\t\t\t\t\t\"displayOrder\": [\n" +
                "\t\t\t\t\t\t\"1\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"fcodeBuyer\": \"true\",\n" +
                "\t\t\t\t\t\"fullTicketStatus\": \"TRADE_SUCCESS\",\n" +
                "\t\t\t\t\t\"hallName\": \"5号厅\",\n" +
                "\t\t\t\t\t\"hasOnlineSales\": \"false\",\n" +
                "\t\t\t\t\t\"modifiedTime\": \"1490012232\",\n" +
                "\t\t\t\t\t\"payEndTime\": \"1490012228\",\n" +
                "\t\t\t\t\t\"poster\": \"i1/TB1f5IXXXXXXXbJXFXXXXXXXXXX_.jpg\",\n" +
                "\t\t\t\t\t\"seatList\": [\n" +
                "\t\t\t\t\t\t\"7排7座\",\n" +
                "\t\t\t\t\t\t\"7排8座\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"showEndTime\": \"1490056380\",\n" +
                "\t\t\t\t\t\"showId\": \"129346948\",\n" +
                "\t\t\t\t\t\"showName\": \"魔兽\",\n" +
                "\t\t\t\t\t\"showTime\": \"1490049000\",\n" +
                "\t\t\t\t\t\"status\": \"CANUSE\",\n" +
                "\t\t\t\t\t\"tbOrderId\": \"194773400420231\",\n" +
                "\t\t\t\t\t\"ticketContent\": \"{\\\"codes\\\":{\\\"取票号\\\":\\\"14014307\\\"},\\\"info\\\":\\\"请您使用【取票号】到影院【大地数字影院】自助终端机或柜台取票，并对号入座。如有问题致电淘宝客服：0571-88157838\\\"}\",\n" +
                "\t\t\t\t\t\"version\": \"国语 英语 3D\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"bizType\": \"SEAT\",\n" +
                "\t\t\t\t\t\"cinemaId\": \"11095105\",\n" +
                "\t\t\t\t\t\"cinemaName\": \"大地新电商测试影院\",\n" +
                "\t\t\t\t\t\"codes\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"code\": \"WKFZGG3\",\n" +
                "\t\t\t\t\t\t\t\"name\": \"取票号\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"count\": \"2\",\n" +
                "\t\t\t\t\t\"createTime\": \"1490008404\",\n" +
                "\t\t\t\t\t\"displayOrder\": [\n" +
                "\t\t\t\t\t\t\"1\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"fcodeBuyer\": \"true\",\n" +
                "\t\t\t\t\t\"fullTicketStatus\": \"REFUNDING\",\n" +
                "\t\t\t\t\t\"hallName\": \"5号厅\",\n" +
                "\t\t\t\t\t\"hasOnlineSales\": \"false\",\n" +
                "\t\t\t\t\t\"modifiedTime\": \"1490012231\",\n" +
                "\t\t\t\t\t\"payEndTime\": \"1490008461\",\n" +
                "\t\t\t\t\t\"poster\": \"i1/TB1f5IXXXXXXXbJXFXXXXXXXXXX_.jpg\",\n" +
                "\t\t\t\t\t\"seatList\": [\n" +
                "\t\t\t\t\t\t\"5排7座\",\n" +
                "\t\t\t\t\t\t\"5排8座\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"showEndTime\": \"1490056380\",\n" +
                "\t\t\t\t\t\"showId\": \"129346948\",\n" +
                "\t\t\t\t\t\"showName\": \"魔兽\",\n" +
                "\t\t\t\t\t\"showTime\": \"1490049000\",\n" +
                "\t\t\t\t\t\"status\": \"CANUSE\",\n" +
                "\t\t\t\t\t\"tbOrderId\": \"1490008404303999\",\n" +
                "\t\t\t\t\t\"ticketContent\": \"{\\\"codes\\\":{\\\"取票号\\\":\\\"WKFZGG3\\\"},\\\"info\\\":\\\"请您使用【取票号】到影院【大地数字影院】自助终端机或柜台取票，并对号入座。如有问题致电淘宝客服：0571-88157838\\\"}\",\n" +
                "\t\t\t\t\t\"version\": \"国语 英语 3D\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"bizType\": \"SEAT\",\n" +
                "\t\t\t\t\t\"cinemaId\": \"11095105\",\n" +
                "\t\t\t\t\t\"cinemaName\": \"大地新电商测试影院\",\n" +
                "\t\t\t\t\t\"codes\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"code\": \"14014307\",\n" +
                "\t\t\t\t\t\t\t\"name\": \"取票号\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"count\": \"1\",\n" +
                "\t\t\t\t\t\"createTime\": \"1490002405\",\n" +
                "\t\t\t\t\t\"displayOrder\": [\n" +
                "\t\t\t\t\t\t\"1\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"fcodeBuyer\": \"true\",\n" +
                "\t\t\t\t\t\"fullTicketStatus\": \"TRADE_SUCCESS\",\n" +
                "\t\t\t\t\t\"hallName\": \"3号厅\",\n" +
                "\t\t\t\t\t\"hasOnlineSales\": \"false\",\n" +
                "\t\t\t\t\t\"modifiedTime\": \"1490002411\",\n" +
                "\t\t\t\t\t\"payEndTime\": \"1490002410\",\n" +
                "\t\t\t\t\t\"poster\": \"i1/TB1f5IXXXXXXXbJXFXXXXXXXXXX_.jpg\",\n" +
                "\t\t\t\t\t\"saleGoods\": \"爆米花\",\n" +
                "\t\t\t\t\t\"seatList\": [\n" +
                "\t\t\t\t\t\t\"1排12座\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"showEndTime\": \"1490023680\",\n" +
                "\t\t\t\t\t\"showId\": \"129346948\",\n" +
                "\t\t\t\t\t\"showName\": \"魔兽\",\n" +
                "\t\t\t\t\t\"showTime\": \"1490016300\",\n" +
                "\t\t\t\t\t\"status\": \"CANUSE\",\n" +
                "\t\t\t\t\t\"tbOrderId\": \"1490002405548999\",\n" +
                "\t\t\t\t\t\"ticketContent\": \"{\\\"codes\\\":{\\\"取票号\\\":\\\"14014307\\\"},\\\"info\\\":\\\"请您使用【取票号】到影院【大地数字影院】自助终端机或柜台取票，并对号入座。如有问题致电淘宝客服：0571-88157838\\\"}\",\n" +
                "\t\t\t\t\t\"version\": \"国语 英语 3D\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"bizType\": \"SEAT\",\n" +
                "\t\t\t\t\t\"cinemaId\": \"11095105\",\n" +
                "\t\t\t\t\t\"cinemaName\": \"大地新电商测试影院\",\n" +
                "\t\t\t\t\t\"codes\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"code\": \"14014307\",\n" +
                "\t\t\t\t\t\t\t\"name\": \"取票号\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"count\": \"2\",\n" +
                "\t\t\t\t\t\"createTime\": \"1489993772\",\n" +
                "\t\t\t\t\t\"displayOrder\": [\n" +
                "\t\t\t\t\t\t\"1\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"fcodeBuyer\": \"true\",\n" +
                "\t\t\t\t\t\"fullTicketStatus\": \"REFUNDING\",\n" +
                "\t\t\t\t\t\"hallName\": \"2号厅\",\n" +
                "\t\t\t\t\t\"hasOnlineSales\": \"false\",\n" +
                "\t\t\t\t\t\"modifiedTime\": \"1489996486\",\n" +
                "\t\t\t\t\t\"payEndTime\": \"1489993810\",\n" +
                "\t\t\t\t\t\"poster\": \"i1/TB1f5IXXXXXXXbJXFXXXXXXXXXX_.jpg\",\n" +
                "\t\t\t\t\t\"seatList\": [\n" +
                "\t\t\t\t\t\t\"3排6座\",\n" +
                "\t\t\t\t\t\t\"3排7座\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"showEndTime\": \"1490054880\",\n" +
                "\t\t\t\t\t\"showId\": \"129346948\",\n" +
                "\t\t\t\t\t\"showName\": \"魔兽\",\n" +
                "\t\t\t\t\t\"showTime\": \"1490047500\",\n" +
                "\t\t\t\t\t\"status\": \"CANUSE\",\n" +
                "\t\t\t\t\t\"tbOrderId\": \"1489993772738999\",\n" +
                "\t\t\t\t\t\"ticketContent\": \"{\\\"codes\\\":{\\\"取票号\\\":\\\"14014307\\\"},\\\"info\\\":\\\"请您使用【取票号】到影院【大地数字影院】自助终端机或柜台取票，并对号入座。如有问题致电淘宝客服：0571-88157838\\\"}\",\n" +
                "\t\t\t\t\t\"version\": \"国语 英语 3D\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"ret\": [\n" +
                "\t\t\"SUCCESS::调用成功\"\n" +
                "\t],\n" +
                "\t\"v\": \"5.0\"\n" +
                "}";
        parseJsonToPairs(jsonStr);
    }

}
