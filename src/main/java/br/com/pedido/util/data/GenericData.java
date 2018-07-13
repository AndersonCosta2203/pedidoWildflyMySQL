package br.com.pedido.util.data;

import java.io.Serializable;
import java.util.List;

public interface GenericData<T extends Serializable> {
	
	public List<T> findAll();
	
	public T insert(T obj);
	
	public T update(T obj);
	
	public T findById(Integer id);
	
	public void delete(Integer id);

}
