package org.hdcd.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="reply")
public class Reply 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long replyNo;
	
	@Column(length = 50, nullable = false)
	private String writer;
	
	@NotBlank
	@Column(length = 200, nullable = false)
	private String content;
	
	@CreationTimestamp
	private LocalDateTime regDate;
	@UpdateTimestamp
	private LocalDateTime updDate;
	
	@ManyToOne
	@JoinColumn(name = "boardNo")
	private Board boardNo;
}