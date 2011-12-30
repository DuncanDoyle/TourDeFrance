package org.jboss.hibernate.integration;

import org.hibernate.transaction.JNDITransactionManagerLookup;

	public class JBossAS7TransactionManagerLookup extends JNDITransactionManagerLookup {


        @Override
        protected String getName() {
                  return "java:jboss/TransactionManager";
        }


        @Override
        public String getUserTransactionName() {
                  return "java:jboss/UserTransaction";
        }
}

