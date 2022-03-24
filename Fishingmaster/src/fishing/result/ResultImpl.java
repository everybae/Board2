package fishing.result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import fishing.Query;
import fishing.point.pointVO;

public class ResultImpl implements Query
{
	Connection conn = null;
	Statement stmt = null;
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
				
				String query = "INSERT INTO RESULT VALUES(RESULT_SEQ01.NEXTVAL, ?, ?, ?, SYSDATE, ?, ?)";
				pstmt = conn.prepareStatement(query);
				
				System.out.print("회원번호를 입력해주세요 : \t");
				int res_mnum = Integer.parseInt(br.readLine());
				pstmt.setInt(1, res_mnum);
				
				System.out.print("잡은 어종 이름을 입력해주세요 : ex)하나만 입력 \t");
				String res_fname = br.readLine();
				pstmt.setString(2, res_fname);
				
				System.out.print("낚시를 한 포인트 이름을 입력해주세요 : ex)대진항 \t");
				String res_pointname = br.readLine();
				pstmt.setString(3, res_pointname);
				
				System.out.print("어종 최고 체장을 입력해주세요 : ex)50 \t");
				String getlength = br.readLine();
				pstmt.setString(4, getlength);
				
				System.out.print("잡은 마리수를 입력해주세요 : ex)5 \t");
				String getnum = br.readLine();
				pstmt.setString(5, getnum);
				
				int result = pstmt.executeUpdate();
				
				if(result == 1)
				{
					System.out.println("조과등록이 완료 되었습니다");
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
	public void selectQuery() throws Exception
	{
		while(true)
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try
			{
				String query = "SELECT RES_MNUM, RES_FNAME, GETDATE, GETLENGTH, RANK() OVER (ORDER BY GETLENGTH DESC) AS 순위\r\n" + 
						"FROM RESULT";
				conn = Connection_All.tryConnection();
				pstmt = conn.prepareStatement(query);
				
				rs = pstmt.executeQuery();
				
				System.out.println("\n회원번호\t 어종\t\t 잡은 날짜\t\t 체장\t 순위\n");
				if(!rs.next())
				{
					System.out.println("조회할 데이터가 없습니다");
				}
				else
				{
					do
					{
						for(int i = 1; i <= 5; i++)
						{
							System.out.print(rs.getString(i) + "\t");
							
						}
						System.out.println();
					}while(rs.next());
				}
			}
			catch(Exception e)
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
	
	public List<resultVO> resultList() throws IOException
	{
		Scanner scan = new Scanner(System.in);
		
		List<resultVO> result_list = new ArrayList<>();

		try
		{
			String query = "SELECT m.mnum, m.mname, r.getnum, r.getlength, r.res_fname FROM MEMBER M, RESULT R WHERE M.MNUM = ? and r.res_mnum = ?";
			conn = Connection_All.tryConnection();
			pstmt = conn.prepareStatement(query);
			
			System.out.println("회원번호를 입력해주세요");
			
			int input = scan.nextInt();
			pstmt.setInt(1, input);
			pstmt.setInt(2, input);
			
			rs = pstmt.executeQuery();
			
			if(!rs.next())
			{
				System.out.println("조회할 데이터가 없습니다");
			}
			else
			{
				do
				{
					int mnum = rs.getInt(1);
					String mname = rs.getString(2);
					int getnum = rs.getInt(3);
					int getlength = rs.getInt(4);
					String fname = rs.getString(5);
					
					resultVO rvo = new resultVO();
					
					rvo.setMnum(mnum);
					rvo.setMname(mname);
					rvo.setGetnum(getnum);
					rvo.setGetlength(getlength);
					rvo.setFname(fname);
					
					result_list.add(rvo);
				}while(rs.next());
			}
			
			while(rs.next())
			{
				
			}
		}
		catch(InputMismatchException ime)
		{
			scan = new Scanner(System.in);
			System.out.println("정수만 입력해주세요");
		}
		catch(SQLException e)
		{
			System.out.println("다시 입력해주세요");
		}
		finally
		{
			Connection_All.close(conn, pstmt, rs);
		}
		return result_list;
	}
}