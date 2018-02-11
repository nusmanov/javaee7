package com.nusmanov.common.infoservice.core.jms;


import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * DccMessageProducer creates a map message.
 * This message will be consumed by MessageConsumer
 *
 * @author nusmanov
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MessageProducer {

    private static final Logger LOG = LoggerFactory.getLogger(MessageProducer.class);

    @Resource(mappedName = "java:/jms/queue/InfoQueue")
    Queue queue;

    @Resource(mappedName = "java:/ConnectionFactory")
    ConnectionFactory cf;

    private static final String MESSEAGE_SELECTOR_APP_VERSION = "1.0";
    private static final String MESSAGE_SELECTOR_MESSAGE_TYPE = "info_message";

    public void sendMessage(final Long id, final String cause) {

        LOG.debug("START - MessageProducer - sending a message - with id", id);

        Connection connection = null;
        Session session = null;
        javax.jms.MessageProducer publisher = null;

        try {
            connection = cf.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            publisher = session.createProducer(queue);

            connection.start();

            MapMessage message = session.createMapMessage();
            message.setLong("ID", id);
            message.setString("CAUSE", cause);
            message.setStringProperty("MyVersion", MESSEAGE_SELECTOR_APP_VERSION);
            message.setStringProperty("MessageType", MESSAGE_SELECTOR_MESSAGE_TYPE);

            publisher.send(message);

            LOG.debug("END - MessageProducer - sending a message - id {}", id);

        } catch (Exception exc) {

            LOG.error("Failure", exc);

        } finally {
            closeResources(connection, session, publisher);
        }


    }


    // extract to utils
    private void closeResources(final Connection connection, final Session session, final javax.jms.MessageProducer publisher) {
        if (publisher != null) {
            try {
                publisher.close();
            } catch (JMSException e) {
                LOG.error("JMSException caught while closing MessageProducer: " + e.getMessage(), e);
            }
        }
        if (session != null) {
            try {
                session.close();
            } catch (JMSException e) {
                LOG.error("JMSException caught while closing Session: " + e.getMessage(), e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException e) {
                LOG.error("JMSException caught while closing Connection: " + e.getMessage(), e);
            }
        }
    }


}
