package designpatterns.builder;

/**
 * created by strugkail on 2018/8/8 0008
 * 给出一个抽象接口，以规范产品对象的各个组成成分的建造。
 * 这个接口规定要实现复杂对象的哪些部分的创建，
 * 并不涉及具体的对象部件的创建
 * @author strugkail
 */
public interface PersonBuilder {
    void buildHead();
    void buildBody();
    void buildFoot();
    Person buildPerson();//组装
}
