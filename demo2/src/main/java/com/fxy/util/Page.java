package com.fxy.util;

public class Page {
	private int everyPage;			//ÿҳ��ʾ��¼��
	private int totalCount;			//�ܼ�¼��
	private int totalPage;			//��ҳ��
	private int currentPage;		//��ǰҳ
	private int beginIndex;			//��ѯ��ʼ��
	private boolean hasPrePage;		//�Ƿ�����һҳ
	private boolean hasNextPage;	//�Ƿ�����һҳ
	public Page(int everyPage, int totalCount, int totalPage, 
			int currentPage,int beginIndex, boolean hasPrePage,
			boolean hasNextPage) {	//�Զ��幹�췽��
		this.everyPage = everyPage;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.beginIndex = beginIndex;
		this.hasPrePage = hasPrePage;
		this.hasNextPage = hasNextPage;
	}
	public Page(){}					//Ĭ�Ϲ��캯��
	public int getEveryPage() {		//���ÿҳ��ʾ��¼��
		return everyPage;
	}
	public void setEveryPage(int everyPage) {//����ÿҳ��ʾ��¼��
		this.everyPage = everyPage;
	}
	public int getTotalCount() {//����ܼ�¼��
		return totalCount;
	}
	public void setTotalCount(int totalCount) {//�����ܼ�¼��
		this.totalCount = totalCount;
	}
	public int getTotalPage() {//�����ҳ��
		return totalPage;
	}
	public void setTotalPage(int totalPage) {//������ҳ��
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {//��õ�ǰҳ
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {//���õ�ǰҳ
		this.currentPage = currentPage;
	}
	public int getBeginIndex() {//��ò�ѯ��ʼ��
		return beginIndex;
	}
	public void setBeginIndex(int beginIndex) {//���ò�ѯ��ʼ��
		this.beginIndex = beginIndex;
	}
	public boolean isHasPrePage() {//����Ƿ�����һҳ
		return hasPrePage;
	}
	public void setHasPrePage(boolean hasPrePage) {//�����Ƿ�����һҳ
		this.hasPrePage = hasPrePage;
	}
	public boolean isHasNextPage() {//����Ƿ�����һҳ
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {//�����Ƿ�����һҳ
		this.hasNextPage = hasNextPage;
	}
}
