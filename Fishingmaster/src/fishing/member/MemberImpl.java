package fishing.member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fishing.Connection_All;
import fishing.Query;

/*
 * CREATE TABLE MEMBER(
	MNUM NUMBER(5) PRIMARY KEY, 
	MNAME VARCHAR2(10), 
	AREA VARCHAR2(10), 
	MPHONE VARCHAR2(20), 
	MSEX VARCHAR(5), 
	MCAREER VARCHAR2(10));
 */

public class MemberImpl implements Query
{
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	
	@Override
	public void insertQuery() throws IOException
	{
		while(true)
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try
			{
				conn = Connection_All.tryConnection();
				
				String query = "INSERT INTO MEMBER VALUES(MEMBER_SEQ01.NEXTVAL, ?, ?, ?, ?, ?)";
				
				pstmt = conn.prepareStatement(query);
				
				System.out.print("이름을 입력해주세요 : \t");
				String mname = br.readLine();
				pstmt.setString(1, mname);
				System.out.print("사는 지역을 입력해주세요 : ex)시까지만 입력 \n");
				
				String area = br.readLine();
				pstmt.setString(2, area);
				System.out.print("전화번호를 입력해주세요 : ex)010-1234-5678 \n");
				
				String mphone = br.readLine();
				pstmt.setString(3, mphone);
				System.out.print("성별을 입력해주세요 : ex)남,여 \n");
				
				String msex = br.readLine();
				pstmt.setString(4, msex);
				System.out.print("낚시 경력을 입력해주세요 : ex)1년 3개월 \n");
				
				String mcareer = br.readLine();
				pstmt.setString(5, mcareer);
				
				int result = pstmt.executeUpdate();
				
				if(result == 1)
				{
					System.out.println("회원등록이 완료 되었습니다");
				}
				else
				{
					System.out.println("다시 시도해주세요");
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				Connection_All.close(conn, pstmt, rs);
			}
			System.out.println("메인으로 돌아가시겠습니까? (y/n)");
			String input = br.readLine();
			if(input.equals("y"))
			{
				break;
			}
			else if(input.equals("n"))
			{
				continue;
			}
			else
			{
				System.out.println("잘못 입력했습니다");
				break;
			}
		}
	}
	
	@Override
	public void selectQuery() throws IOException
	{
		while(true)
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try
			{
				String query = "SELECT * FROM MEMBER WHERE MNAME = ?";
				conn = Connection_All.tryConnection();
				pstmt = conn.prepareStatement(query);
				
				System.out.print("조회할 회원이름을 입력해주세요 : ");
				String mname = br.readLine();
				pstmt.setString(1, mname);
				rs = pstmt.executeQuery();
				
				System.out.println("\n회원번호\t 회원이름\t 지역\t 전화번호\t\t 성별\t 경력\n");
				if(!rs.next())
				{
					System.out.println("조회할 데이터가 없습니다");
				}
				else
				{
					do
					{
						for(int i = 1; i <= 6; i++)
						{
							
							System.out.print(rs.getString(i) + "\t");
						}
						System.out.println();
					}while(rs.next());
				}
				rs.close();
				pstmt.close();
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				Connection_All.close(conn, pstmt, rs);
			}
			System.out.println("메인으로 돌아가시겠습니까? (y/n)");
			String input = br.readLine();
			if(input.equals("y"))
			{
				break;
			}
			else if(input.equals("n"))
			{
				continue;
			}
			else
			{
				System.out.println("잘못 입력했습니다");
				break;
			}
		}
	}
}