package com.jraw.android.jonsapp.data.local;

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
            public static final String FNAME = "PEFname";
            public static final String SNAME = "PESname";
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
            //This holds the tel number of the person who created the conversation? Or the id? Or the name?
            public static final String CREATEDBY = "COCreatedBy";
            public static final String DATECREATED = "CODateCreated";
        }
    }

    public static final class PeCoTable {
        public static final String NAME = "peco";

        public static final class Cols {
            public static final String ID = "ID";
            public static final String PEID = "PCPEID";
            public static final String COID = "PCCOID";
        }
    }

    public static final class TelTable {
        public static final String NAME = "tel";

        public static final class Cols {
            public static final String ID = "ID";
            public static final String NUMBER = "TENumber";
        }
    }
    public static final class PeTelTable {
        public static final String NAME = "petel";

        public static final class Cols {
            public static final String ID = "ID";
            public static final String PEID = "PTPEID";
            public static final String TELID = "PTTelID";
        }
    }
    public static final class TxnTable {
        public static final String NAME = "txn";

        public static final class Cols {
            public static final String ID = "ID";
            public static final String IDINPHONE = "TXIDInPhone";
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
