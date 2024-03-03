//package pl.ene.springbootrestjpa.repository;
//
//import java.util.List;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
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
