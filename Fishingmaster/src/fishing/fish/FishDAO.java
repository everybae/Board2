package fishing.fish;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import fishing.Connection_All;

public class FishDAO
{
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	
	public List<FishVO> fishList() throws IOException
	{
		Scanner scan = new Scanner(System.in);
		
		List<FishVO> fish_list = new ArrayList<>();

		while(true)
		{
			try
			{
				String query = "SELECT * FROM FISH WHERE FNAME = ?";
				conn = Connection_All.tryConnection();
				pstmt = conn.prepareStatement(query);
				
				System.out.println("조회할 어종의 번호를 선택해주세요");
				System.out.println("1)감성돔\t 2)쥐노래미       3)볼락");
				System.out.println("4)숭어\t 5)보리멸\t 6)참돔");
				System.out.println("7)농어\t 8)우럭\t 9)넙치");
				System.out.println("10)붕장어\t");
				
				int input = scan.nextInt();
				switch(input)
				{
				case 1:
					pstmt.setString(1, "감성돔");
					break;
				case 2:
					pstmt.setString(1, "쥐노래미");
					break;
				case 3:
					pstmt.setString(1, "볼락");
					break;
				case 4:
					pstmt.setString(1, "숭어");
					break;
				case 5:
					pstmt.setString(1, "보리멸");
					break;
				case 6:
					pstmt.setString(1, "참돔");
					break;
				case 7:
					pstmt.setString(1, "농어");
					break;
				case 8:
					pstmt.setString(1, "우럭");
					break;
				case 9:
					pstmt.setString(1, "넙치");
					break;
				case 10:
					pstmt.setString(1, "붕장어");
					break;
				}
	
				rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					String fname = rs.getString(1);
					String flength = rs.getString(2);
					String forbi = rs.getString(3);
					String bait = rs.getString(4);
					String season = rs.getString(5);
					
					FishVO fvo = new FishVO();
					
					fvo.setFname(fname);
					fvo.setFlength(flength);
					fvo.setForbi(forbi);
					fvo.setBait(bait);
					fvo.setSeason(season);
					
					fish_list.add(fvo);
				}
			}
			catch(InputMismatchException ime)
			{
				scan = new Scanner(System.in);
				System.out.println("1 ~ 10 사이의 정수만 입력해주세요\n");
				continue;
			}
			catch(SQLException e)
			{
				System.out.println("다시 입력해주세요\n");
				continue;
			}
			finally
			{
				Connection_All.close(conn, pstmt, rs);
			}
			return fish_list;
		}
	}
	
	public void fishprint(String fname, String flength, String forbi, String bait, String season)
	{
		System.out.println("\t" + fname + "\n" + "금지체장 : " + flength + "\n" + "금어기 : " + forbi + "\n" + "미끼 : " +  bait + "\n" + "제철 : " + season);

	}
}