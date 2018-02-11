package com.nusmanov.common.infoservice.core.jms;


import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Message-Driven Bean implementation
 *
 * @author nusmanov
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/InfoQueue"),
        @ActivationConfigProperty(propertyName = "user", propertyValue = "queue_user"),
        @ActivationConfigProperty(propertyName = "password", propertyValue = "very_secret"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "MyAppVersion='1.0' OR MessageType='info_message'"),
        @ActivationConfigProperty(propertyName = "maxSession", propertyValue = "1")})
public class MessageConsumer implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(MessageConsumer.class);


    /**
     * Passes a message to the listener.
     *
     * @param message the message passed to the listener
     */
    @Override
    public void onMessage(final Message message) {
        LOG.info("called with message: {}", message);

        Long id;
        String cause;

        try {

            MapMessage mapMessage = (MapMessage) message;

            LOG.info("calling with values: {}", mapMessage);

            id = (Long) mapMessage.getObject("ID");
            cause = mapMessage.getString("CAUSE");

            // now you can do something with datas from the message

        } catch (Exception e) {
            LOG.error("Failure with message " + message, e);
            throw new EJBException(e);
        }

    }
}
