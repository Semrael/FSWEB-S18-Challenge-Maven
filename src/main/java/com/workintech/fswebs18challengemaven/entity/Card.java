package com.workintech.fswebs18challengemaven.entity;


import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="card",schema="fsweb")
public class Card{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="value")
    private Integer value;
    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name="color")
    @Enumerated(EnumType.STRING)
    private Color color;

//    public void setValue(Integer value){
//        if(type==Type.JOKER){
//            this.value=null;
//        }
//        this.value=value;;
//        this.type=null;
//    }
//
//    public void setType(Type type){
//        if(type==Type.JOKER){
//            this.value=null;
//            this.color=null;
//        }
//        else{
//            this.type=type;
//            this.value=null;
//        }
//
//    }
}
