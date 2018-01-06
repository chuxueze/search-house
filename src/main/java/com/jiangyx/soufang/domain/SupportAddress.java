package com.jiangyx.soufang.domain;

import javax.persistence.*;

@Entity
@Table(name = "support_address")
public class SupportAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 上一级行政单位
    @Column(name = "belong_to")
    private String belongTo;

    // 英文名
    @Column(name = "en_name")
    private String enName;

    // 中文名
    @Column(name = "cn_name")
    private String cnName;

    private String level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 行政级别定义
     */
    public enum Level{
        CITY("city"),
        REGION("region");

        private String vale;

        Level(String level) {
            this.vale = level;
        }

        public String getVale() {
            return vale;
        }

        public void setVale(String vale) {
            this.vale = vale;
        }

        public static Level of(String value) {
            for (Level level : Level.values()) {
                if (level.getVale().equals(value)) {
                    return level;
                }
            }
            throw new IllegalArgumentException();
        }
    }
}
