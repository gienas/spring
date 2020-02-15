//package pl.ene.springbootrestjpa.repository;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//import org.springframework.stereotype.Repository;
//
//import pl.ene.springbootrestjpa.domain.Customer;
//import pl.ene.springbootrestjpa.domain.Customer_;
//
//@Repository
//public class CustomerManualRepositoryImpl implements CustomerManualRespository {
//
//	@PersistenceContext
//	private EntityManager em;
//
//	@Override
//	public List<Customer> getByName(String name) {
//		CriteriaBuilder builder = em.getCriteriaBuilder();
//		CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
//		Root<Customer> root = query.from(Customer.class);
//		Predicate condition = builder.like( builder.lower(root.get(Customer_.name)),  name.toLowerCase() +"%");
//		query.where(condition);
//		TypedQuery<Customer> q = em.createQuery(query);
//		return q.getResultList();
//	}
//
//}
