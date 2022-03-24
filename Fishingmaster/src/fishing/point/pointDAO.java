package fishing.point;

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

public class pointDAO
{
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	
	public List<pointVO> pointList() throws IOException
	{
		Scanner scan = new Scanner(System.in);
		
		List<pointVO> point_list = new ArrayList<>();

		while(true)
		{
			try
			{
				String query = "SELECT * FROM POINT WHERE POINTNAME = ?";
				conn = Connection_All.tryConnection();
				pstmt = conn.prepareStatement(query);
				
				System.out.println("낚시 포인트의 번호를 입력해주세요");
				System.out.println("1)대진항\t 2)울산 당사방파제\t 3)속초 장사항방파제");
				
				int input = scan.nextInt();
				if(input == 1)
				{
					pstmt.setString(1, "대진항");
				}
				else if(input == 2)
				{
					pstmt.setString(1, "울산 당사방파제");
				}
				else if(input == 3)
				{
					pstmt.setString(1, "속초 장사항방파제");
				}
				else
				{
					System.out.println("잘못 입력했습니다\n");
					continue;
				}
				rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					String pointname = rs.getString(1);
					String pointlocation = rs.getString(2);
					String fname = rs.getString(3);
					
					pointVO pvo = new pointVO();
					
					pvo.setpName(pointname);
					pvo.setpLocation(pointlocation);
					pvo.setfName(fname);
					
					point_list.add(pvo);
				}
			}
			catch(InputMismatchException ime)
			{
				scan = new Scanner(System.in);
				System.out.println("1 ~ 3 사이의 정수만 입력해주세요\n");
				continue;
			}
			catch(SQLException e)
			{
				System.out.println("다시 입력해주세요");
			}
			finally
			{
				Connection_All.close(conn, pstmt, rs);
			}
			return point_list;
		}
	}
	
	public void pointprint(String pname, String location)
	{
		System.out.println("포인트 이름 : " + pname + "\n포인트 위치 : " + location + "\n어종이름 ");
	}
}