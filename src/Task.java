import java.util.Date;

public class Task implements Cloneable{
        private String description;
        private Date date;

        public Task(String description, Date date){
                this.description=description;
                this.date=date;
        }

        public String getDescription(){
                return this.description;
        }

        public Date getDate(){
                return this.date;
        }

}

