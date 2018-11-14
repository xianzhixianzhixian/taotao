package com.taotao.portal.pojo;

/**
 * 购物车商品
 * @author: xianzhixianzhixian
 * @date: 2018/11/14 8:39 PM
 */
public class CartItem {

    private Long id; /* 商品id */
    private String title; /* 商品标题 */
    private Integer num; /* 商品数量 */
    private Long price; /* 商品价格 */
    private String image; /* 商品图片链接 */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
