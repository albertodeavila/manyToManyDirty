package manytomany

import grails.core.GrailsApplication
import manyToMany.Listener
import org.grails.datastore.mapping.core.Datastore
import org.springframework.context.ApplicationContext
import org.springframework.context.event.AbstractApplicationEventMulticaster
import org.springframework.context.support.AbstractApplicationContext

class BootStrap {

    GrailsApplication grailsApplication

    def init = { servletContext ->
        setupAuditListener()


        Person person = new Person(name: 'alber').addToPositions(new Position(name: 'boss')).save(flush: true)

        person.addToPositions(new Position(name: 'FOO')).save(flush: true)
        person.addToPositions(new Position(name: 'FOO2')).save(flush: true)

        Person person2 = new Person(name: 'alber2').addToPositions(new Position(name: 'boss2')).save(flush: true)
        Position foo3 = person2.positions.first()

        person.addToPositions(foo3)
        println person.isDirty()
        println person.dirtyPropertyNames
        person.save(flush: true)
    }


    def setupAuditListener() {
        ApplicationContext ctx = grailsApplication.mainContext
        AbstractApplicationContext context = grailsApplication.mainContext
        AbstractApplicationEventMulticaster multicaster = context.applicationEventMulticaster
        List listeners = multicaster.applicationListeners
        multicaster.removeAllListeners()

        ctx.getBeansOfType(Datastore).values().each { Datastore datastore ->
            Listener auditListener = new Listener(datastore)
            multicaster.addApplicationListener(auditListener)
        }

        listeners.each {
            multicaster.addApplicationListener(it)
        }
    }

    def destroy = {
    }
}
