package cn.prm.server.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.BaseController;
import cn.prm.server.commons.Constants.RESPONSE_CODE;
import cn.prm.server.dto.BaseDto;
import cn.prm.server.dto.CountryDto;
import cn.prm.server.dto.ListDto;
import cn.prm.server.exception.BusinessException;
import cn.prm.server.form.CountryForm;
import cn.prm.server.service.CountryService;

@RestController
@RequestMapping("/api/country")
public class CountryApiController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(CountryApiController.class);

	@Autowired
	CountryService countryService;

	@RequestMapping("/init")
	public Object init(HttpServletRequest request) {
		CurrUser currUser = getCurrUser(request);
		if (currUser == null) {
			return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
		}
		ListDto<CountryDto> list = countryService.getAll();
		if(list.getRows()!=null && list.getRows().size()>0){
			return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您已经初始化过国家");
		}
		String dataStr = "AE,United Arab Emirates,阿联酋;AF,Afghanistan,阿富汗;AL,Albania,阿尔巴尼亚;AO,Angola,安哥拉;AR,Argentina,阿根廷;AT,Austria,奥地利;AU,Australia,澳大利亚;AZ,Azerbaijan,阿塞拜疆;BD,Bangladesh,孟加拉;BE,Belgium,比利时;BG,Bulgaria,保加利亚;BH,Bahrain,巴林;BI,Burundi,布隆迪;BJ,Benin,贝宁;BM,Bermuda,百慕大;BN,Brunei,文莱;BO,Bolivia,玻利维亚;BR,Brazil,巴西;BS,Bahamas,巴哈马;BT,Bhutan,不丹;BW,Botswana,博茨瓦纳;CA,Canada,加拿大;CF,Central Africa,中非共和国;CG,Congo,刚果;CH,Switzerland,瑞士;CK,Cook Is.,库克群岛;CL,Chile,智利;CM,Cameroon,喀麦隆;CN,China,中国;CO,Colombia,哥伦比亚;CR,Costa Rica,哥斯达黎加;CU,Cuba,古巴;CV,Cape Verde Is.,佛得角群岛;CY,Cyprus,塞浦路斯;CZ,Czech,捷克共和国;DE,Germany,德国;DK,Denmark,丹麦;DZ,Algeria,阿尔及利亚;EC,Ecuador,厄瓜多尔;EE,Estonia,爱沙尼亚;EG,Egypt,埃及;ES,Spain,西班牙;ET,Ethiopia,埃塞俄比亚;FI,Finland,芬兰;FJ,Fiji,斐济;FR,France,法国;GA,Gabon,加蓬;GB,Great Britain,英国;GD,Grenada,格林纳达;GH,Ghana,加纳;GM,Gambia,冈比亚;GN,Guinea-Bissau,几内亚;GQ,Equatorial Guinea,赤道几内亚;GR,Greece,希腊;GT,Guatemala,危地马拉;GU,Guam,关岛;GY,Guyana,圭亚那;HK,Hong kong,香港;HN,Honduras,洪都拉斯;HR,Croatia,克罗地亚;HT,Haiti,海地;HU,Hungary,匈牙利;ID,Indonesia,印度尼西亚;IE,Ireland,爱尔兰;IL,Israel,以色列;IN,India,印度;IQ,Iraq,伊拉克;IR,Iran,伊朗;IS,Iceland,冰岛;IT,Italy,意大利;JM,Jamaica,牙买加;JO,Jordan,约旦;JP,Japan,日本;KE,Kenya,肯尼亚;KH,Cambodia,柬埔寨;KP,R.O.Korea,韩国;KR,D.P.R.Korea,北朝鲜;KW,Kuwait,科威特;KZ,Kazakhstan,哈萨克斯坦;LA,Laos,老挝;LB,Lebanon,黎巴嫩;LT,Lithuania,立陶宛;LU,Luxembourg,卢森堡;LV,Latvia,拉托维亚;LY,Libya,利比亚;MA,Morocco,摩洛哥;MC,Monaco,摩纳哥;MD,Moldova,摩尔多瓦;MG,Madagascar,马达加斯加;ML,Mali,马里;MN,Mongolia,蒙古;MO,Macao,澳门;MR,Mauritania,毛里塔尼亚;MT,Malta,马耳他;MU,Mauritius,毛里求斯;MV,Maldives,马尔代夫;MX,Mexico,墨西哥;MY,Malaysia,马来西亚;MZ,Mozambique,莫桑比克;NA,Namibia,纳米比亚;NE,Niger,尼日尔;NG,Nigeria,尼日利亚;NI,Nicaragua,尼加拉瓜;NL,Netherlands,荷兰;NO,Norway,挪威;NP,Nepal,尼泊尔;NZ,New Zealand,新西兰;OM,Oman,阿曼;PA,Panama,巴拿马;PE,Peru,秘鲁;PG,Papua New Guinea,巴布亚新几内亚;PH,Philippines,菲律宾;PK,Pakistan,巴基斯坦;PL,Poland,波兰;PT,Portugal,葡萄牙;PY,Paraguay,巴拉圭;QA,Qatar,卡塔尔;RO,Romania,罗马尼亚;RU,Russia,俄罗斯;RW,Rwanda,卢旺达;SA,Saudi Arabia,沙特阿拉伯;SD,Sudan,苏丹;SE,Sweden,瑞典;SG,Singapore,新加坡;SK,Slovakia,斯洛伐克;SM,San Marino,圣马力诺;SN,Senegal,塞内加尔;SO,Somalia,索马里;SY,Syria,叙利亚;TH,Thailand,泰国;TJ,Tadzhikistan,塔吉克斯坦;TM,Turkmenistan,土库曼斯坦;TN,Tunisia,突尼斯;TO,Tonga,汤加;TW,Taiwan,台湾;TZ,Tanzania,坦桑尼亚;UA,Ukraine,乌克兰;UG,Uganda,乌干达;UK,United Kingdom,英国;US,United States,美国;UY,Uruguay,乌拉圭;UZ,Uzbekistan,乌兹别克斯坦;VA,Vatican City,梵蒂冈;VE,Venezuela,委内瑞拉;VN,Viet Nam,越南;YE,Yemen,也门;YU,Yugoslavia,南斯拉夫;ZA,South Africa,南非;ZM,Zambia,赞比亚;ZR,Zaire,扎伊尔;ZW,Zimbabwe,津巴布";
		String[] counrties = dataStr.split(";");
		int totalCount = counrties.length;
		int successCount = 0;
		for (String country : counrties) {
			String[] names = country.split(",");
			if(names.length!=3){
				continue;
			}
			CountryForm form = new CountryForm();
			form.setStdName(names[0]);
			form.setEnName(names[1]);
			form.setCnName(names[2]);
			form.setCode(0);
			try {
				countryService.createCountry(currUser, form);
				successCount++;
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		String res = "初始化国家数据成功，共"+totalCount+"条,成功"+successCount+"条";
		
		return new BaseDto(RESPONSE_CODE.CODE_SUCCESS, res);
	}

	@RequestMapping("/list")
	public ListDto<CountryDto> list() {
		ListDto<CountryDto> list = countryService.getAll();
		list.setCode(RESPONSE_CODE.CODE_SUCCESS);
		return list;
	}
}
