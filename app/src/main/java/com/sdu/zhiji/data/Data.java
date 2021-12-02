package com.sdu.zhiji.data;

import com.sdu.zhiji.R;
import com.sdu.zhiji.dao.Descriptor;
import com.sdu.zhiji.dao.DescriptorCard;
import com.sdu.zhiji.dao.Question;

public class Data {

    public static DescriptorCard[] descriptorCards = {
            new DescriptorCard(1, R.drawable.desc1, "",""),
            new DescriptorCard(2, R.drawable.desc2, "",""),
            new DescriptorCard(3, R.drawable.desc3, "",""),
            new DescriptorCard(4, R.drawable.desc4, "","")
    };

    public static Question[] questions = {
            new Question(1,"当你在社会上的时候，你通常更喜欢:","参与一般谈话","分别与每个人交谈")
            , new Question(2,"你更像是那种人:", "现实的", "有理论倾向的")
            , new Question(3,"在你看来更糟的是:", "为生活漂泊", "稳定的生活")
            , new Question(4,"你给人的印象更深刻的是:", "有原则", "情绪化")
            , new Question(5,"你更感兴趣的是:", "有说服力", "感人")
            , new Question(6,"如果你必须做一些不寻常的工作，你会选择:", "事先计划好", "在工作中找出需要做什么")
            , new Question(7,"你倾向于做出选择:", "小心", "冲动")
            , new Question(8,"在派对上", "你玩到很晚，你变得越来越活跃", "早点离开，感觉很累")
            , new Question(9,"你更感兴趣的是:", "现实主义者", "想象力丰富的人")
            , new Question(10,"你更感兴趣的是:", "实际存在的",	"可能存在的")
            , new Question(11,"你对人的判断是基于:", "规则比情况更复杂", "情况比规则更普遍")
            , new Question(12,"对于其他人，你通常会站在何种立场", "客观", "主观")
            , new Question(13,"你经常这样做:", "守时", "迟到")
            , new Question(14,"你喜欢:", "提前完成工作", "把事情推迟到最后一刻")
            , new Question(15,"在你的朋友中，你是:", "知道关于每个人的新闻", "你是最后一个知道发生了什么事的人")
            , new Question(16,"做你的日常工作让你感觉更好的是:", "使它成为普遍接受的方式", "发明自己的方法")
            , new Question(17,"当你喜欢读书的时候，你喜欢作家:", "他说得很清楚，他是认真的", "и以一种不同寻常的、原创的方式表达思想")
            , new Question(18,"你更感兴趣的是什么:", "推理的顺序和逻辑", "人类关系的和谐")
            , new Question(19,"你更容易做出判断的是:", "基于逻辑的", "基于价值观的")
            , new Question(20,"你更喜欢哪种情况:", "确定的和完成的", "不确定和不完整")
            , new Question(21,"你可能会说你更像:",	"严肃意志坚定的人",	"容易相处的人")
            , new Question(22,"你打电话时", "你很少想知道会发生什么", "提前想你要说什么")
	        , new Question(23,"讲述事实时:",	"为自己说话",	"说明规律")
	        , new Question(24,"梦想家和幻想家:",	"让你烦恼", "被迷住了")
            , new Question(25,"你更像哪种人:", "冷静", "亲切体贴")
            , new Question(26,"你认为情况会更糟的是:", "不明智", "消极")
            , new Question(27,"在大多数情况下，你会:",	"控制事态的发展", "依靠事件的自然进程")
            , new Question(28,"当你感觉更好的是:", "已经买了", "有机会购买")
	        , new Question(29,"和你在一起谈话时:",	"你是这次谈话的主导者:", "你等着别人跟你说话")
            , new Question(30,"基于常识的断言:", "很少有人怀疑", "经常引起怀疑")
            , new Question(31,"你希望你的朋友是哪种人:", "一个脚踏实地的人", "总是有新想法的人。")
            , new Question(32,"当你做决定的时候，你更容易处理:",	"规章制度",	"感觉")
            , new Question(33,"你更像哪种人:",	"强硬的",	"软弱的")
            , new Question(34,"你更喜欢哪种能力:", "有针对性的组织", "利用现有的机会")
            , new Question(35,"你认为在哪种情况下更有价值:", "清晰", "未知")
            , new Question(36,"与人新的和不寻常的互动:", "刺激你，让你充满活力", "让你疲惫不堪，消耗你的精力")
            , new Question(37,"在大多数情况下，你是那种人:", "现实", "带着梦想和幻想")
            , new Question(38,"你更有可能去发现:", "别人能为你做什么", "别人的观点")
            , new Question(39,"你会得到更多的满足：:", "仔细讨论问题", "就正在讨论的问题达成一致")
            , new Question(40,"你的行为更受哪里指挥:", "你的头脑", "你的内心")
            , new Question(41,"当你知道在特定的时间，你会做特定的事情:", "你很高兴你能计划你的时间", "你不喜欢被束缚")
            , new Question(42,"通常你:", "努力取得预期的成果", "你对事情的结果很满意。")
            , new Question(43,"你喜欢:", "很多朋友，短暂的关系。", "几个朋友，长期的关系。")
            , new Question(44,"你的领导能力强吗:", "确实", "否定")
            , new Question(45,"你更感兴趣的是:", "产品的生产和销售", "产品的研究与设计")
            , new Question(46,"你认为这是一种赞美吗:", "合乎逻辑的人", "敏感的人")
            , new Question(47,"你对自己的评价哪个更高:", "果断", "忠诚")
            , new Question(48,"你更喜欢哪样的说法:",	"最终",	"试验和初步的")
            , new Question(49,"你感觉好多了:",	"在做出决定之后",	"在做出决定之前")
            , new Question(50,"与你几乎不认识的人交流:", "你很容易进行长时间的谈话。", "你很难找到合适的话题")
            , new Question(51,"你更信任:", "自己的经验", "你的直觉")
            , new Question(52,"你宁愿被认为是:", "实际的人", "足智多谋的人")
            , new Question(53,"你认为一个更值得称赞的人是:", "头脑清醒的", "有强烈感情的")
            , new Question(54,"你更有可能是:", "公正公正", "有同情心的人")
            , new Question(55,"你认为坚持计划是:", "对你有利", "限制你")
            , new Question(56,"你做得更好的是:",	"遵循一个经过深思熟虑的计划", "应付意外")
            , new Question(57,"你身边的人知道你的想法吗:", "知道关于大多数事情", "除非你告诉他们")
            , new Question(58,"你更喜欢:", "强烈的感觉", "想象力")
            , new Question(59,"如果你是一名教师，你会选择教授:", "实际物体", "学科理论")
            , new Question(60,"你认为哪个是一个更大的错误:", "太暴躁", "太客观")
            , new Question(61,"你认为自己:",	"头脑清醒",	"仁慈而富有同情心")
            , new Question(62,"你更喜欢哪种情况:",	"有序的", "混乱和没有计划")
            , new Question(63,"你认为你的行为会:",	"根据规则", "古怪")
	        , new Question(64,"通常你是:",	"外向的人",	"冷静而克制")
            , new Question(65,"当你写作时，你更喜欢:",	"表达字面意思",	"表达内心想法")
            , new Question(66,"你很难:", "感到与他人团结一致", "利用周围的人")
	        , new Question(67,"你会希望自己有:", "更清晰的头脑", "更大的同情能力 ")
	        , new Question(68,"你宁愿和主管一起工作，因为:", "谁总是公平的", "永远祝福者")
	        , new Question(69,"你喜欢:", "预定事件", "意外事件")
	        , new Question(70,"你更有可能会有:", "考虑周到的行为", "自发的行为")
    };

    public static Descriptor[] descriptors = {
			new Descriptor(1
					, new String[]{"E", "I"}
					, new int[]{1, 8, 15, 22, 29, 36, 43, 50, 57, 64}),
			new Descriptor(2
					, new String[]{"S", "N"}
					, new int[]{2, 3, 9, 10, 16, 17, 23, 24, 30, 31, 37
								, 38, 44, 45, 51, 52, 58, 59, 65, 66}),
			new Descriptor(3
					, new String[]{"T", "F"}
					, new int[]{4, 5, 11, 12, 18, 19, 25, 26, 32, 33
								, 39, 40, 46, 47, 53, 54, 60, 61, 67, 68}),
			new Descriptor(4
					, new String[]{"J", "P"}
					, new int[]{6, 7, 13, 14, 20, 21, 27, 28, 34, 35
								, 41, 42, 48, 49, 55, 56, 62, 63, 69, 70})
	};

	public static String[] pages = {
			"ESTJ", "ENTJ", "ISTJ", "ENFJ", "ESFJ", "INTJ", "INFJ", "ISFJ", "ENTP"
			, "ESTP", "ENFP", "ESFP", "INTP", "ISTP", "INFP", "ISFP"
	};

}
