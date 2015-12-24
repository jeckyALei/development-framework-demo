//package com.mwh.springboot.dao;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import javax.transaction.Transactional;
//
//import org.hibernate.Session;
//import org.springframework.stereotype.Repository;
//
//import com.mwh.springboot.entity.TestEntity;
//
///*@Repository*/
//public class TransferBlockDao {
//
////	@PersistenceContext
//	protected EntityManager em;
//	/**
//	 * 存储
//	 * 
//	 * @param entity
//	 */
////	@Transactional
//	public void save(TestEntity entity) {
//		if (entity != null) {
//			em.merge(entity);
//		}
//	}
//
//	/**
//	 * 批量存储
//	 * 
//	 * @param entities
//	 */
////	@Transactional
//	public void save(List<TestEntity> entities) {
//		Session session = (Session) em.getDelegate();
//		int num = 0;
//		int batchSize = 50;
//		for (TestEntity entity : entities) {
//			num++;
//			session.merge(entity);
//			if (num % batchSize == 0) {
//				session.flush();
//				session.clear();
//			}
//		}
//
//	}
//
//	/**
//	 * 根据主键获取实体
//	 * 
//	 * @param primaryKey
//	 * @return
//	 */
//	public TestEntity find(Serializable primaryKey) {
//		return em.find(TestEntity.class, primaryKey);
//	}
//
//	/**
//	 * 获取指定数量的实体
//	 * 
//	 * @param sqlString
//	 * @param blockCount
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public List<TestEntity> list(String sqlString, int blockCount) {
//		Query query = em.createQuery(sqlString);
//		query.setMaxResults(blockCount);
//		return query.getResultList();
//	}
//
//	/**
//	 * 获取所有TestEntity实体
//	 * @return
//	 * @throws Exception
//	 */
//	@SuppressWarnings("unchecked")
//	public List<TestEntity> listByTaskId() throws Exception {
//		String sqlString = "from TestEntity as entity ";
//		Query query = em.createQuery(sqlString);
//		return query.getResultList();
//	}
//	/**
//	 * 
//	 * @param id
//	 * @return
//	 * @throws Exception
//	 */
//	public long getTestEntitySize() throws Exception {
//		String sqlString = "select count(entity.id) from TestEntity as entity ";
//		Query query = em.createQuery(sqlString);
//		return (Long) query.getSingleResult();
//	}
//	/**
//	 * 根据id获取实体
//	 * @param id
//	 * @return
//	 */
//	public TestEntity getFirstBlockEntity(String id) {
//		String sqlString = "from TestEntity as entity where entity.id='" + id + "'";
//		List<TestEntity> entities = list(sqlString, 1);
//		if (entities != null && entities.size() > 0) {
//			return entities.get(0);
//		} else {
//			return null;
//		}
//	}
//}
