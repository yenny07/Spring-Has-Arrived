package jpabook.yenyang.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQuery;

import jpabook.yenyang.domain.Member;
import jpabook.yenyang.domain.Order;
import jpabook.yenyang.domain.OrderSearch;
import jpabook.yenyang.domain.QMember;
import jpabook.yenyang.domain.QOrder;


public interface OrderRepository extends JpaRepository<Order, Long> {

}
