package com.yl.study.solr.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class MyPage<T> implements Page<T>,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4450495099144152198L;
	
	private int pageNum;
	private List<T> list;
	private int pageSize;
	private int totalPages;
	private int rowCount;
	
	/**
	 * 初始化Page
	 * @param pageNum		页号
	 * @param pageSize		页长
	 * @param list			数据列表
	 * @param rowCount		数据总条数
	 */
	public MyPage(int pageNum, int pageSize, List<T> list, int rowCount){
		this.list = list;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.rowCount = rowCount;
		this.totalPages = pageSize > 0 ? (int)Math.ceil(((double) rowCount)/pageSize) : 0;
	}
	
	@Override
	public List<T> getContent() {
		return list;
	}

	@Override
	public int getNumber() {
		return pageNum;
	}

	@Override
	public int getNumberOfElements() {
		return list.size();
	}

	@Override
	public int getSize() {
		return pageSize;
	}

	@Override
	public Sort getSort() {
		return null;
	}

	@Override
	public long getTotalElements() {
		return rowCount;
	}

	@Override
	public int getTotalPages() {
		return totalPages;
	}

	@Override
	public boolean hasContent() {
		if(list != null){
			return true;
		}
		return false;
	}

	@Override
	public boolean hasNext() {
		if(totalPages-1 > pageNum){
			return true;
		}
		return false;
	}

	@Override
	public boolean hasPrevious() {
		if(pageNum > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean isFirst() {
		if(pageNum > 0){
			return false;
		}
		return true;
	}

	@Override
	public boolean isLast() {
		if(totalPages == 0 || totalPages-1 == pageNum){
			return true;
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return null;
	}

	@Override
	public Pageable nextPageable() {
		return null;
	}

	@Override
	public Pageable previousPageable() {
		return null;
	}

	@Override
	public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
		return null;
	}

}
