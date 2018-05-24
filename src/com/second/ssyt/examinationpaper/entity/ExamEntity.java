package com.second.ssyt.examinationpaper.entity;

public class ExamEntity {
    private int id;
    private String examClassroom;
    private int examPaperId;
    private int toClassId;
    private int state;

    public int getToClassId() {
        return toClassId;
    }

    public void setToClassId(int toClassId) {
        this.toClassId = toClassId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExamClassroom() {
        return examClassroom;
    }

    public void setExamClassroom(String examClassroom) {
        this.examClassroom = examClassroom;
    }

    public int getExamPaperId() {
        return examPaperId;
    }

    public void setExamPaperId(int examPaperId) {
        this.examPaperId = examPaperId;
    }
}
/*
public class PageModel<T>{
	private List<T> list;
	
	private int pageNo;
	
	private int pageSize;
	
	private int allRecords;
	
	public PageModel(){
		super();
	}
	
	public PageModel(List<T> list, int pageNo, int pageSize, int allRecords){
		super();
		this.list = list;
		this.pageNo = pageSize;
		this.pageSize = pageSize;
		this.allRecords = allRecords;
	}
	
	public int getTotalPage(){
		return (allRecords + pageSize-1)/pageSize;
		//return (int) Math.ceil((double) allRecords / pageSize);
	}
	
	public int getFirst(){
		return 1;
	}
	
	public int getPre(){
		if(pageNo ==1){
			return 1;
		}
		return pageNo - 1;
	}
	
	public int getNext(){
		if(pageNo == getTotalPage()){
			return getTotalPage();
		}
		return pageNo + 1;
	}
	
	public int getLast(){
		return getTotalPage();
	}
	
	
	
	
	
	
	public List<T> getList(){
		return list;
	}
	

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getAllRecords() {
		return allRecords;
	}

	public void setAllRecords(int allRecords) {
		this.allRecords = allRecords;
	}

	public void setList(List<T> list) {
		this.list = list;
	}	
}

*/














