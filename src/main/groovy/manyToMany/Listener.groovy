package manyToMany

import manytomany.Person
import org.grails.datastore.mapping.core.Datastore
import org.grails.datastore.mapping.engine.event.AbstractPersistenceEvent
import org.grails.datastore.mapping.engine.event.EventType
import org.grails.orm.hibernate.event.listener.HibernateEventListener

class Listener extends HibernateEventListener {

	protected Listener(Datastore datastore) {
		super(datastore)
	}

	@Override
	protected void onPersistenceEvent(AbstractPersistenceEvent event) {
//		if(event.eventType == EventType.PreUpdate) {
			Object entity = event.entityObject
			println "--------------------------------------------------------"
			println "Event: ${event.eventType}"
			println "Object: $entity"

			if (entity instanceof Person) {
				println entity.isDirty('positions')
				println entity.positions.size()
			}
			println "--------------------------------------------------------"
//		}
	}
}