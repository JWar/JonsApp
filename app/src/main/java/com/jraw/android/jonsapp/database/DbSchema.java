package com.jraw.android.jonsapp.database;

/**
 * Created by JonGaming on 29/06/2017.
 */

public class DbSchema {
    /**
     * Created by JonGaming on 25/11/2016.
     * Defines/handles database
     */

    public static final class PersonTable {
        public static final String NAME = "person";

        public static final class Cols {
            public static final String ID = "ID";
            public static final String FIRSTNAME = "PEFirstname";
            public static final String SURNAME = "PESurname";
        }
    }

    public static final class MsgTable {
        public static final String NAME = "msg";

        public static final class Cols {
            public static final String ID = "ID";
            public static final String COID = "MSCOID";
            public static final String TOID = "MSToID";
            public static final String FROMID = "MSFromID";
            public static final String BODY = "MSBody";
            public static final String EVENTDATE = "MSEventDate";
            public static final String TYPE = "MSType";
            public static final String DATA = "MSData";
            public static final String RESULT = "MSResult";
        }
    }

    public static final class ConversationTable {
        public static final String NAME = "conversation";

        public static final class Cols {
            public static final String ID = "ID";
            public static final String TITLE = "COTitle";
            //This is the public id of the conversation shared by all persons IN the conversation.
            //Cant use the id as this could clash.
            public static final String PUBLICID = "COPublicId";
            //This holds the tel number of the person who created the conversation? Or the id? Or the name?
            public static final String CREATEDBY = "COCreatedBy";
            public static final String DATECREATED = "CODateCreated";
        }
    }
    //This is needed to link Persons with Conversation. Basically all people in the conversation!
    public static final class PeCoTable {
        public static final String NAME = "peco";

        public static final class Cols {
            public static final String ID = "ID";
            public static final String PEID = "PCPEId";
            public static final String COPUBLICID = "PCCOPublicId";
        }
    }

    public static final class TelTable {
        public static final String NAME = "tel";

        public static final class Cols {
            public static final String ID = "ID";
            public static final String NUMBER = "TENumber";
        }
    }
    //This links Persons with their tel numbers. Done in such a way as to allow multiple tels per person.
    //Not sure how the app deals with it. Cant remember how Whatsapp deal with that..
    public static final class PeTelTable {
        public static final String NAME = "petel";

        public static final class Cols {
            public static final String ID = "ID";
            public static final String PEID = "PTPEId";
            public static final String TELID = "PTTelId";
        }
    }
    /**
     * TODO: 180102_ how to handle TelTo, i.e. sending this to all people in conversation
     * Its easy if its one person... its just their tel or their id. Would it be best to
     * have TelTo as a list of persons involved? This means the server needs to keep a record
     * of tels to persons, but it should be doing that anyway...
     * So TelTo becomes a list of tels to send this message to!
     */
    public static final class TxnTable {
        public static final String NAME = "txn";

        public static final class Cols {
            public static final String ID = "ID";
            //180102_This is required so the phone that sent the txn knows which txn to delete if successful!
            //No. This is redundant. This is needed in other app due to the way it works but not in JonsApp
            //Hmm need Id of data, change this to DATAIDINPHONE so have quick way of accessing msg id.
            //Urgh need to think about this.
            //TODO: how to sort updating a msg's status as delivered/sent
            //Needs msg id
//            public static final String IDINPHONE = "TXIdInPhone";
            public static final String TELFROM = "TXTelFrom";
            public static final String TELTO = "TXTelTo";
            public static final String DATA = "TXData";
            public static final String TYPE = "TXType";
            public static final String TIMESTAMP = "TXTimestamp";
            public static final String SENT = "TXSent";
            public static final String DELIVERED = "TXDelivered";
            public static final String RESULT = "TXResult";
            public static final String ERROR = "TXError";
        }
    }
}
