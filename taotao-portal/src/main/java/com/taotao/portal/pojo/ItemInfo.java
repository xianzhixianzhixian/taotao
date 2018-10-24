package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

/**
 * @author: xianzhixianzhixian on 2018/10/24
 */
public class ItemInfo extends TbItem {

    public String[] getImages() {
        String image = getImage();
        if (image!=null && image.trim().length()!=0){
            String[] images = image.split(",");
            return images;
        }
        return null;
    }
}
