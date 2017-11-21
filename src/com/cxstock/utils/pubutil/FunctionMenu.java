package com.cxstock.utils.pubutil;

import java.util.List;

@SuppressWarnings("unchecked")
public class FunctionMenu {

	private String id; // 节点id
	private List menu;
	private List items;
	private String text; // 节点名称
	private String href;
	private boolean collapsed;
	public FunctionMenu() {
		super();
	}

	public FunctionMenu(String id, List menu,List items,String text,String href) {
		super();
		this.id = id;
		this.menu= menu;
		this.items=items;
		this.text = text;
		this.href = href;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List getMenu() {
		return menu;
	}

	public void setMenu(List menu) {
		this.menu = menu;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}

}