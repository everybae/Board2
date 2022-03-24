package fishing;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import fishing.fish.FishDAO;
import fishing.fish.FishVO;
import fishing.member.MemberImpl;
import fishing.point.pointDAO;
import fishing.point.pointVO;
import fishing.result.ResultImpl;
import fishing.result.resultVO;


public class Home
{
	public static void main(String[] args) throws Exception
	{
		Scanner scan = new Scanner(System.in);
		Menu_Frame menu = new Menu_Frame();
		MemberImpl m = new MemberImpl();
		pointDAO pDAO = new pointDAO();
		ResultImpl r = new ResultImpl();
		FishDAO fDAO = new FishDAO();
		
		while(true)
		{
			try
			{
				menu.menuFrame();
				System.out.println("실행할 기능의 번호를 입력해주세요");
				int input = scan.nextInt();
				scan.nextLine();
				
				if(input == 1)
				{
					m.insertQuery();
				}
				else if(input == 2)
				{
					m.selectQuery();
				}
				else if(input == 3)
				{
					List<pointVO> plist = pDAO.pointList();
					String pname = plist.get(1).getpName();
					String location = plist.get(1).getpLocation();
					
					pDAO.pointprint(pname, location);
					for(int i = 0; i < plist.size(); i++)
					{
						String fname = plist.get(i).getfName();
						
						System.out.print(fname + "\t");
					}
					System.out.println();
				}
				else if(input == 4)
				{
					List<FishVO> flist = fDAO.fishList();
					
					for(int i = 0; i < flist.size(); i++)
					{
						String fname = flist.get(i).getFname();
						String flength = flist.get(i).getFlength();
						String forbi = flist.get(i).getForbi();
						String bait = flist.get(i).getBait();
						String season = flist.get(i).getSeason();
						
						fDAO.fishprint(fname, flength, forbi, bait, season);
					}
				}
				else if(input == 5)
				{
					r.insertQuery();
				}
				else if(input == 6)
				{
					List<resultVO> rlist = r.resultList();
					
					System.out.println("회원번호\t 회원이름\t 잡은 마리수\t 최고 길이\t 어종이름\t");
					for(int i = 0; i < rlist.size(); i++)
					{
						int mnum = rlist.get(i).getMnum();
						String mname = rlist.get(i).getMname();
						int getnum = rlist.get(i).getGetnum();
						int getlength = rlist.get(i).getGetlength();
						String fname = rlist.get(i).getFname();
						
						System.out.println(mnum + "\t" + mname + "\t" + getnum + "\t" + getlength + "\t" + fname);
					}
				}
				else if(input == 7)
				{
					r.selectQuery();
				}
				else if(input == 8)
				{
					System.out.println("프로그램을 종료합니다.");
					break;
				}
				else
				{
					System.out.println("1 ~ 8 사이의 정수만 입력해주세요");
					continue;
				}
			}
			catch(InputMismatchException ime)
			{
				scan = new Scanner(System.in);
				System.out.println("1 ~ 8 사이의 정수만 입력해주세요");
			}
		}
	}
}