package com.fc.common;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 分页信息
 */
@SuppressWarnings("rawtypes")
public class PageList implements Serializable {
	
    private static final long serialVersionUID = -2429864663690465105L;

    /** 分页大小   */
    private int limit;
    
    /** 页数 */
    private int page;
    
    /** 总记录数 */
    private long totalCount;
    
    /** 总页数   */
    private int totalPages;
    
    /** 开始行号，从0开始 */
    private int startRow;
    
    /** 结束行号，结果不包含本行号 */
    private int endRow;
    
    /** 记录集 */
	private List items;
    

    @SuppressWarnings("unchecked")
	public PageList(Page page) {
    	
        this.totalCount = page.getTotal();
        this.totalPages = page.getPages();
        this.page = page.getPageNum();
        this.limit = page.getPageSize();
        
        this.startRow = (int) page.getStartRow();
        this.endRow = (int) page.getEndRow();
        
        List list = new ArrayList();
        for(Object item : page) list.add(item);
        
        this.items = list;
    }

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @return the totalCount
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @return the startRow
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * @return the endRow
	 */
	public int getEndRow() {
		return endRow;
	}

	/**
	 * @return the items
	 */
	public List getItems() {
		return items;
	}
}
