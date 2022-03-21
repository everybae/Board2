//package org.hdcd.domain;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
//
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//@Entity
//@Table(name="reply")
//public class Reply 
//{
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long replyNo;
//	
//	@Column(length = 50, nullable = false)
//	private String writer;
//	
//	@NotBlank
//	@Column(length = 500, nullable = false)
//	private String content;
//	
//	@CreatedDate
//	private String regDate;
//	
//	@LastModifiedDate
//	private String updDate;
//	
//	@ManyToOne
//	@JoinColumn(name = "boardNo")
//	private Board boardNo;
//}